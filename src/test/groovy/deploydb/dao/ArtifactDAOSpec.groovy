package deploydb.dao

import spock.lang.*

import io.dropwizard.hibernate.AbstractDAO
import org.hibernate.SessionFactory
import org.hibernate.Criteria

import deploydb.models.Artifact

class ArtifactDAOSpec extends Specification {
    private ArtifactDAO dao
    private SessionFactory sessionFactory = Mock(SessionFactory)

    def "ensure the constructor works"() {
        given:
        ArtifactDAO dao

        when:
        dao = new ArtifactDAO(sessionFactory)

        then:
        dao instanceof AbstractDAO
    }

    def "findByGroupAndName() should return null if there are no results"() {
        given:
        ArtifactDAO dao = Spy(ArtifactDAO, constructorArgs: [sessionFactory])
        def criteria = Mock(Criteria)
        criteria./add|set|addOrder|setMaxResults/(_) >> criteria
        _ * dao.criteria() >> criteria
        1 * criteria.list() >> []

        expect:
        dao.findByGroupAndName('spock-group', 'spock-name') == null
    }
}
