package deploydb.models

import spock.lang.*
import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.configuration.ConfigurationParsingException
import io.dropwizard.configuration.ConfigurationValidationException 
import deploydb.ModelLoader
import deploydb.registry.ModelRegistry
import deploydb.models.Environment

class EnvironmentSpec extends Specification {
    def "ensure Environment object can be instantiated"() {
        when:
        def environment = new Environment()

        then:
        environment instanceof Environment
    }
}

class EnvironmentWithArgsSpec extends Specification {
    private final ModelRegistry<Environment> environmentRegistry =
            new ModelRegistry<>()
    private final ModelLoader<Environment> environmentLoader =
            new ModelLoader<>(Environment.class)

    def "Loading of valid environment config file suceeds"() {
        given:
        String fileContents = """
description: "DeployDB Primary Integration"
webhooks:
  deployment:
    created:
      - http://jenkins.example.com/job/integ-deploy-created/build
    completed:
      - http://jenkins.example.com/job/integ-deploy-completed/build
"""
        Environment environment = environmentLoader.loadFromString(fileContents)
        environment.ident = "integ"
        environmentRegistry.put(environment.ident, environment)

        expect:
        environment.ident == 'integ'
        environment.description == 'DeployDB Primary Integration'
        environment.webhooks.deployment.created[0] ==
                "http://jenkins.example.com/job/integ-deploy-created/build"
        environment.webhooks.deployment.completed[0] ==
                "http://jenkins.example.com/job/integ-deploy-completed/build"
        environmentRegistry.get('integ') == environment
        environmentRegistry.getAll() == [environment]
    }

    def "Loading an empty environment config file throws a parsing exception"() {
        when:
        Environment environment = environmentLoader.loadFromString("")

        then:
        thrown(ConfigurationParsingException)
    }

    def "Loading a malformed environment config file throws throws a parsing exception"() {
        when:
        Environment environment = environmentLoader.loadFromString('j&&&&')

        then:
        thrown(ConfigurationParsingException)
    }

    def "Loading a invalid environment config file throws throws a validation exception"() {
        when:
        String fileContents = """
webhooks:
  deployment:
    created:
      - http://jenkins.example.com/job/integ-deploy-created/build
    completed:
      - http://jenkins.example.com/job/integ-deploy-completed/build
"""
        Environment environment = environmentLoader.loadFromString(fileContents)

        then:
        /**
         *  Exception thrown because, description is mandatory
         */
        thrown(ConfigurationValidationException)
    }

    def "Inserting an empty object in Environment Registry throws null pointer exception"() {
        when:
        Environment environment = new Environment()
        environmentRegistry.put(environment.ident, environment)

        then:
        thrown(NullPointerException)
    }
}
