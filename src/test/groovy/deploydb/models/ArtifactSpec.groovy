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
        this.artifact = new Artifact(12, 'spock.group', 'spock-test-name')
    }

    def "its properties should be correct"() {
        expect:
        artifact.id == 12
        artifact.group == 'spock.group'
        artifact.name == 'spock-test-name'
    }
}
