package deploydb.resources

import io.dropwizard.testing.junit.ResourceTestRule
import org.junit.Rule
import spock.lang.*
import static org.mockito.Mockito.*

import deploydb.models.Artifact
import deploydb.dao.ArtifactDAO

class ArtifactResourceSpec extends Specification {
    private ArtifactDAO dao = mock(ArtifactDAO.class)

    @Rule
    ResourceTestRule resources = ResourceTestRule.builder()
                .addResource(new ArtifactResource(dao)).build();

    def setup() {
        reset(dao)
    }

    def teardown() {
    }

    def "byIdentifier when the item exists"() {
        given:
        Long artifactId = 12
        Artifact artifact = new Artifact('spock.group', 'spock-artifact')
        Artifact fetched = null
        when(dao.findById(artifactId)).thenReturn(artifact)

        when:
        fetched = resources.client()
                           .resource("/api/v1/artifacts/${artifactId}")
                           .get(Artifact.class)

        then:
        fetched.name == artifact.name
        fetched.group == artifact.group
    }
}
