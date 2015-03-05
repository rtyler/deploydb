package deploydb.resources

import deploydb.DeployDBApp
import deploydb.WorkFlow
import io.dropwizard.testing.junit.ResourceTestRule
import javax.ws.rs.client.Client
import org.junit.Rule
import spock.lang.*

import deploydb.models.Artifact
import deploydb.dao.ArtifactDAO

class ArtifactResourceSpec extends Specification {
    def app = new DeployDBApp()
    private WorkFlow workFlow = new WorkFlow(app)
    private ArtifactDAO dao = Mock(ArtifactDAO)

    @Rule
    ResourceTestRule dropwizard = ResourceTestRule.builder()
                .addResource(new ArtifactResource(workFlow)).build();

    def "ensure it can be instantiated"() {
        when:
        def artifactResource = new ArtifactResource(workFlow)

        then:
        artifactResource instanceof ArtifactResource
    }

    def "byIdentifier when the item exists"() {
        given:
        Client client = dropwizard.client()
        workFlow.artifactDAO = dao
        Long artifactId = 12
        Artifact artifact = new Artifact('spock.group', 'spock-artifact', '1.0.1',
                                         'http://example.com/cucumber.jar')
        Artifact fetched = null
        1 * dao.get(artifactId) >> artifact

        when:
        fetched = client.target(dropwizard.jerseyTest.baseUri)
                        .path("/api/artifacts/${artifactId}")
                        .request()
                        .get(Artifact.class)

        then:
        fetched == artifact
    }
}
