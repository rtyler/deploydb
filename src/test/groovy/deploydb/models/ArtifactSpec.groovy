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
