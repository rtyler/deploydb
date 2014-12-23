package deploydb.dao

import spock.lang.*
import static org.mockito.Mockito.*

import io.dropwizard.hibernate.AbstractDAO
import org.hibernate.SessionFactory

class ArtifactDAOSpec extends Specification {
    private ArtifactDAO dao
    private SessionFactory sessionFactory = mock(SessionFactory.class)

    def setup() {
        dao = new ArtifactDAO(sessionFactory)
    }

    def "ensure the constructor works"() {
        expect:
        dao instanceof AbstractDAO
    }
}
