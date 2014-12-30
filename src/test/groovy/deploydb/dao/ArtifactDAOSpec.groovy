package deploydb.dao

import spock.lang.*

import io.dropwizard.hibernate.AbstractDAO
import org.hibernate.SessionFactory

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

    def "findById should delegate to get()"() {
        given:
        ArtifactDAO dao = Spy(ArtifactDAO, constructorArgs: [sessionFactory])
        Long artifactId = 1337
        1 * dao.get(artifactId) >> null

        expect:
        dao.findById(artifactId) == null
    }
}
