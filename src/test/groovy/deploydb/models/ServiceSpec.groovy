package deploydb.models

import spock.lang.*
import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.configuration.ConfigurationParsingException
import io.dropwizard.configuration.ConfigurationValidationException 
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
            new ModelRegistry<Service>(Service.class)

    def "Loading of valid service config file suceeds"() {
        given:
        Service service = serviceRegistry.load("services/test-valid.yml")
        serviceRegistry.put(service.name, service)

        expect:
        service.name == "Fun as a Service"
        service.artifacts[0] == "com.github.lookout:foas"
        service.artifacts[1] == "com.github.lookout.puppet:puppet-foas"
        service.artifacts[2] == "com.github.lookout:puppet-mysql"
        service.pipelines[0] == "devtoprod"
        service.promotions[0] == "status-check"
        service.promotions[1] == "jenkins-smoke"
        serviceRegistry.get("Fun as a Service") == service
    }

    def "Loading an empty service config file throws a null pointer exception"() {
        when:
        Service service = serviceRegistry.load("services/test-empty.yml")

        then:
        thrown(NullPointerException)
    }

    def "Loading a malformed service config file throws throws a parsing exception"() {
        when:
        Service service = serviceRegistry.load("services/test-malformed.yml")

        then:
        thrown(ConfigurationParsingException)
    }

    def "Loading a invalid service config file throws throws a validation exception"() {
        when:
        Service service = serviceRegistry.load("services/test-invalid.yml")

        then:
        thrown(ConfigurationValidationException)
    }

    def "Inserting an empty object in Service Registry throws null pointer exception"() {
        when:
        Service service = new Service()
        serviceRegistry.put(service.name, service)

        then:
        thrown(NullPointerException)
    }
}
