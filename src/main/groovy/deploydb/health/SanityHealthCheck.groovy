package deploydb.health

import com.codahale.metrics.health.HealthCheck
import com.codahale.metrics.health.HealthCheck.Result

/**
 * The SanityHealthCheck is 100% guaranteed to work and only exists to ensure
 * that at least *some* health check gets registered for the application.
 *
 */
class SanityHealthCheck extends HealthCheck {

    /**
     * Simple check, always healthy
     */
    @Override
    Result check() throws Exception {
        return Result.healthy()
    }
}
