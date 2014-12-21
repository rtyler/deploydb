package deploydb.health

import spock.lang.*

import com.codahale.metrics.health.HealthCheck.Result

class SanityHealthCheckSpec extends Specification {
    def "ensure it always is healthy"() {
        when:
        def check = new SanityHealthCheck()

        then:
        check.check() == Result.healthy()
    }
}
