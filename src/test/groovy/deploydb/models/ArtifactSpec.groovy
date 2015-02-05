package deploydb.models

import spock.lang.*

class ArtifactSpec extends Specification {
    def "ensure it can be instantiated"() {
        when:
        def artifact = new Artifact()

        then:
        artifact instanceof Artifact
    }
}

class ArtifactWithArgsSpec extends Specification {
    private Artifact artifact

    def setup() {
        artifact = new Artifact('spock.group',
                                'spock-test-name',
                                'v1.0.0',
                                'http://example.com/cucumber.jar')
    }

    def "its properties should be correct"() {
        expect:
        artifact.group == 'spock.group'
        artifact.name == 'spock-test-name'
        artifact.version == 'v1.0.0'
    }
}
