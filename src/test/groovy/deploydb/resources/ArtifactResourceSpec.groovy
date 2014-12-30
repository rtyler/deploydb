package deploydb.resources

import io.dropwizard.testing.junit.ResourceTestRule
import org.junit.Rule
import spock.lang.*

import deploydb.models.Artifact
import deploydb.dao.ArtifactDAO

class ArtifactResourceSpec extends Specification {
    private ArtifactDAO dao = Mock(ArtifactDAO)

    @Rule
    ResourceTestRule resources = ResourceTestRule.builder()
                .addResource(new ArtifactResource(dao)).build();

    def "byIdentifier when the item exists"() {
        given:
        Long artifactId = 12
        Artifact artifact = new Artifact('spock.group', 'spock-artifact', '1.0.1')
        Artifact fetched = null
        1 * dao.findById(artifactId) >> artifact

        when:
        fetched = resources.client()
                           .resource("/api/v1/artifacts/${artifactId}")
                           .get(Artifact.class)

        then:
        fetched == artifact
    }
}
