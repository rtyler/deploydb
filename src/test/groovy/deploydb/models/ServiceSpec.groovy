package deploydb.models

import spock.lang.*
import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.configuration.ConfigurationParsingException
import io.dropwizard.configuration.ConfigurationValidationException 
import deploydb.ModelLoader
import deploydb.registry.ModelRegistry
import deploydb.models.Service

class ServiceSpec extends Specification {
    def "ensure Service object can be instantiated"() {
        when:
        def service = new Service()

        then:
        service instanceof Service
    }
}

class ServiceWithArgsSpec extends Specification {
    private final ModelRegistry<Service> serviceRegistry =
            new ModelRegistry<>()
    private final ModelLoader<Service> serviceLoader =
            new ModelLoader<>(Service.class)

    def "Loading of valid service config file suceeds"() {
        given:
        String fileContents = """
description: "Fun as a Service"
artifacts:
  - com.github.lookout:foas
  - com.github.lookout.puppet:puppet-foas
  - com.github.lookout:puppet-mysql
pipelines:
  - devtoprod
promotions:
  - status-check
  - jenkins-smoke
"""
        Service service = serviceLoader.loadFromString(fileContents)
        service.ident = "test-valid"
        serviceRegistry.put(service.ident, service)

        expect:
        service.ident == 'test-valid'
        service.description == 'Fun as a Service'
        service.artifacts[0] == 'com.github.lookout:foas'
        service.artifacts[1] == 'com.github.lookout.puppet:puppet-foas'
        service.artifacts[2] == 'com.github.lookout:puppet-mysql'
        service.pipelines[0] == 'devtoprod'
        service.promotions[0] == 'status-check'
        service.promotions[1] == 'jenkins-smoke'
        serviceRegistry.get('test-valid') == service
        serviceRegistry.getAll() == [service]
    }

    def "Loading an empty service config file throws a parsing  exception"() {
        when:
        Service service = serviceLoader.loadFromString('')

        then:
        thrown(ConfigurationParsingException)
    }

    def "Loading a malformed service config file throws throws a parsing exception"() {
        when:
        String fileContents = "j&&&&"
        Service service = serviceLoader.loadFromString(fileContents)

        then:
        thrown(ConfigurationParsingException)
    }

    def "Loading a invalid service config file throws throws a validation exception"() {
        when:
        String fileContents = "description: Boop"
        Service service = serviceLoader.loadFromString(fileContents)

        then:
        thrown(ConfigurationValidationException)
    }

    def "Inserting an empty object in Service Registry throws null pointer exception"() {
        when:
        Service service = new Service()
        serviceRegistry.put(service.ident, service)

        then:
        thrown(NullPointerException)
    }
}
