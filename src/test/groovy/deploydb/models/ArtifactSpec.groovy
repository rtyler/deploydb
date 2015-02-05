package deploydb.models

import spock.lang.*

class ArtifactSpec extends Specification {

    private Artifact artifact

    def setup() {
        artifact = new Artifact('spock.group',
                                'spock-test-name',
                                'v1.0.0',
                                'http://example.com/cucumber.jar')
    }

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
        given:
        Artifact artifact = new Artifact('spock.group',
                                         'spock-test-name',
                                         'v1.0.0',
                                         'http://example.com/cucumber.jar')

        expect:
        artifact.group == 'spock.group'
        artifact.name == 'spock-test-name'
        artifact.version == 'v1.0.0'
        artifact.sourceUrl == 'http://example.com/cucumber.jar'
    }

    def "equals passes for artifacts with same versions"() {
        given:
        Artifact firstArtifact = new Artifact()
        firstArtifact.version = "v1.0.0"
        Artifact secondArtifact = new Artifact()
        secondArtifact.version = "v1.0.0"

        expect:
        firstArtifact == secondArtifact
    }

    def "equals passes for artifacts with same sourceUrls"() {
        given:
        Artifact firstArtifact = new Artifact()
        firstArtifact.sourceUrl = "http://example.com/cucumber.jar"
        Artifact secondArtifact = new Artifact()
        secondArtifact.sourceUrl = "http://example.com/cucumber.jar"

        expect:
        firstArtifact == secondArtifact
    }

    def "hash compare passes for artifacts with same versions"() {
        given:
        Artifact firstArtifact = new Artifact()
        firstArtifact.version = "v1.0.0"
        Artifact secondArtifact = new Artifact()
        secondArtifact.version = "v1.0.0"

        expect:
        firstArtifact.hashCode() == secondArtifact.hashCode()
    }

    def "hash compare passes for artifacts with same sourceUrls"() {
        given:
        Artifact firstArtifact = new Artifact()
        firstArtifact.sourceUrl = "http://example.com/cucumber.jar"
        Artifact secondArtifact = new Artifact()
        secondArtifact.sourceUrl = "http://example.com/cucumber.jar"

        expect:
        firstArtifact.hashCode() == secondArtifact.hashCode()
    }

    def "equals fails for artifacts with different versions"() {
        given:
        Artifact firstArtifact = new Artifact()
        firstArtifact.version = "v1.0.0"
        Artifact secondArtifact = new Artifact()
        secondArtifact.version = "v2.0.0"

        expect:
        firstArtifact != secondArtifact
    }

    def "equals fails for artifacts with different sourceUrls"() {
        given:
        Artifact firstArtifact = new Artifact()
        firstArtifact.sourceUrl = "http://example.com/cucumber1.jar"
        Artifact secondArtifact = new Artifact()
        secondArtifact.sourceUrl = "http://example.com/cucumber2.jar"

        expect:
        firstArtifact != secondArtifact
    }

    def "hash compare fails for artifacts with different versions"() {
        given:
        Artifact firstArtifact = new Artifact()
        firstArtifact.version = "v1.0.0"
        Artifact secondArtifact = new Artifact()
        secondArtifact.version = "v2.0.0"

        expect:
        firstArtifact.hashCode() != secondArtifact.hashCode()
    }

    def "hash compare fails for artifacts with different sourceUrls"() {
        given:
        Artifact firstArtifact = new Artifact()
        firstArtifact.sourceUrl = "http://example.com/cucumber1.jar"
        Artifact secondArtifact = new Artifact()
        secondArtifact.sourceUrl = "http://example.com/cucumber2.jar"

        expect:
        firstArtifact.hashCode() != secondArtifact.hashCode()
    }
}
