package deploydb.resources

import io.dropwizard.testing.junit.ResourceTestRule
import javax.ws.rs.client.Client
import org.junit.Rule
import spock.lang.*

import deploydb.models.Service
import deploydb.registry.ModelRegistry

class ServiceResourceSpec extends Specification {
    private ModelRegistry<Service> serviceRegistry = Mock(ModelRegistry)

    @Rule
    ResourceTestRule dropwizard = ResourceTestRule.builder()
                .addResource(new ServiceResource(serviceRegistry)).build();

    def "ServiceResource byName returns valid item when exists"() {
        given:
        Client client = dropwizard.client()
        String serviceIdent = 'faas'
        Service service = new Service('faas', 'Fun-as-a-Service',
                                      [ 'com.github.lookout:foas',
                                        'com.github.lookout.puppet:puppet-foas',
                                        'com.github.lookout:puppet-mysql' ],
                                      [ 'detoprod' ],
                                      [ 'status-check',
                                        'jenkins-smoke' ])        
        Service fetched = null
        1 * serviceRegistry.get(serviceIdent) >> service

        when:
        fetched = client.target(dropwizard.jerseyTest.baseUri)
                        .path("/api/services/${serviceIdent}")
                        .request()
                        .get(Service.class)

        then:
        fetched == service
    }
}
