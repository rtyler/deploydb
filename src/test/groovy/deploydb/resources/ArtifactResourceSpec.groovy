package deploydb.resources

import io.dropwizard.testing.junit.ResourceTestRule
import javax.ws.rs.client.Client
import org.junit.Rule
import spock.lang.*

import deploydb.models.Artifact
import deploydb.dao.ArtifactDAO
import deploydb.WebhookManager

class ArtifactResourceSpec extends Specification {
    private ArtifactDAO dao = Mock(ArtifactDAO)

    private final WebhookManager webhookManager = new WebhookManager()
    @Rule
    ResourceTestRule dropwizard = ResourceTestRule.builder()
                .addResource(new ArtifactResource(dao, webhookManager)).build();

    def "byIdentifier when the item exists"() {
        given:
        Client client = dropwizard.client()
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
