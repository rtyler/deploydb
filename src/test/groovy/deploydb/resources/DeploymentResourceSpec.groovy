package deploydb.resources

import deploydb.Status
import deploydb.WorkFlow
import deploydb.models.Artifact
import deploydb.models.PromotionResult
import io.dropwizard.testing.junit.ResourceTestRule
import javax.ws.rs.client.Client
import org.junit.Rule
import spock.lang.*

import deploydb.models.Deployment
import deploydb.dao.DeploymentDAO

class DeploymentResourceSpec extends Specification {
    private WorkFlow workFlow = Mock(WorkFlow)

    @Rule
    ResourceTestRule dropwizard = ResourceTestRule.builder()
                .addResource(new DeploymentResource(workFlow)).build();

    def "ensure it can be instantiated"() {
        when:
        def deploymentResource = new DeploymentResource(workFlow)

        then:
        deploymentResource instanceof DeploymentResource
    }

/* COMMENTED OUT TEMPORARILY

    def "byIdentifier when the item exists"() {
        given:
        Client client = dropwizard.client()

        Artifact a1 = new Artifact()
        Deployment deployment = new Deployment(a1,
                "pre-prod",
                "faas",
                Status.STARTED)

        Long deploymentId = 12
        Deployment fetched = null
        1 * dao.get(deploymentId) >> deployment

        when:
        fetched = client.target(dropwizard.jerseyTest.baseUri)
                        .path("/api/deployments/${deploymentId}")
                        .request()
                        .get(Deployment.class)

        then:
        fetched == deployment
    }
    */
}
