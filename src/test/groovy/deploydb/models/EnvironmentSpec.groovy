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
                "description: \"Fun as a Environment\"\n" +
                "artifacts:\n" +
                "  - com.github.lookout:foas\n" +
                "  - com.github.lookout.puppet:puppet-foas\n" +
                "  - com.github.lookout:puppet-mysql\n" +
                "pipelines:\n" +
                "  - devtoprod\n" +
                "promotions:\n" +
                "  - status-check\n" +
                "  - jenkins-smoke"
        Environment environment = environmentLoader.loadFromString(fileContents)
        environment.ident = "test-valid"
        environmentRegistry.put(environment.ident, environment)

        expect:
        environment.ident == 'test-valid'
        environment.description == 'Fun as a Environment'
        environment.artifacts[0] == 'com.github.lookout:foas'
        environment.artifacts[1] == 'com.github.lookout.puppet:puppet-foas'
        environment.artifacts[2] == 'com.github.lookout:puppet-mysql'
        environment.pipelines[0] == 'devtoprod'
        environment.promotions[0] == 'status-check'
        environment.promotions[1] == 'jenkins-smoke'
        environmentRegistry.get('test-valid') == environment
        environmentRegistry.getAll() == [environment]
    }

    def "Loading an empty environment config file throws a null pointer exception"() {
        when:
        String fileContents = ""
        Environment environment = environmentLoader.loadFromString(fileContents)
        //Environment environment = environmentLoader.load('environments/test-empty.yml')

        then:
        thrown(NullPointerException)
    }

    def "Loading a malformed environment config file throws throws a parsing exception"() {
        when:
        String fileContents = "j&&&&"
        Environment environment = environmentLoader.loadFromString(fileContents)
        //Environment environment = environmentLoader.load('environments/test-malformed.yml')

        then:
        thrown(ConfigurationParsingException)
    }

    def "Loading a invalid environment config file throws throws a validation exception"() {
        when:
        String fileContents = "description: Boop"
        Environment environment = environmentLoader.loadFromString(fileContents)
        //Environment environment = environmentLoader.load('environments/test-invalid.yml')

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
