package deploydb.resources

import io.dropwizard.testing.junit.ResourceTestRule
import javax.ws.rs.client.Client
import org.junit.Rule
import spock.lang.*

import deploydb.models.Environment
import deploydb.registry.ModelRegistry

class EnvironmentResourceSpec extends Specification {
    private ModelRegistry<deploydb.models.Environment> environmentRegistry = Mock(ModelRegistry)

    @Rule
    ResourceTestRule dropwizard = ResourceTestRule.builder()
                .addResource(new EnvironmentResource(environmentRegistry)).build();

    def "EnvironmentResource byName returns valid item when exists"() {
        given:
        Client client = dropwizard.client()
        String environmentIdent = 'integ'
        deploydb.models.Environment environment = new deploydb.models.Environment()
        environment.ident = environmentIdent
        Environment fetched = null
        1 * environmentRegistry.get(environmentIdent) >> environment

        when:
        fetched = client.target(dropwizard.jerseyTest.baseUri)
                        .path("/api/environments/${environmentIdent}")
                        .request()
                        .get(Environment.class)

        then:
        fetched == environment
    }
}
