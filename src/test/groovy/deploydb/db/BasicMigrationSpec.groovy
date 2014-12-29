package deploydb

import spock.lang.*

import org.flywaydb.core.Flyway


class BasicMigrationSpec extends Specification {
    private Flyway flyway = null

    def setup() {
        flyway = new Flyway()
    }

    def "ensure we can migrate from zero to the latest"() {
        when:
        flyway.setDataSource('jdbc:h2:mem:',
                             '',
                             '')

        then:
        flyway instanceof Flyway
        flyway.migrate()
    }
}
