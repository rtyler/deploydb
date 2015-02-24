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
        String fileContents =
                "description: \"DeployDB Primary Integration\"\n" +
                "webhooks:\n" +
                "  deployment:\n" +
                "    created:\n" +
                "      - http://jenkins.example.com/job/integ-deploy-created/build\n" +
                "    completed:\n" +
                "      - http://jenkins.example.com/job/integ-deploy-completed/build"
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

    def "Loading an empty environment config file throws a null pointer exception"() {
        when:
        String fileContents = ""
        Environment environment = environmentLoader.loadFromString(fileContents)

        then:
        thrown(NullPointerException)
    }

    def "Loading a malformed environment config file throws throws a parsing exception"() {
        when:
        String fileContents = "j&&&&"
        Environment environment = environmentLoader.loadFromString(fileContents)

        then:
        thrown(ConfigurationParsingException)
    }

    def "Loading a invalid environment config file throws throws a validation exception"() {
        when:
        String fileContents =
                "webhooks:\n" +
                "  deployment:\n" +
                "    started:\n" +
                "      - http://jenkins.example.com/job/integ-deploy-started/build\n"
        Environment environment = environmentLoader.loadFromString(fileContents)

        then:
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
