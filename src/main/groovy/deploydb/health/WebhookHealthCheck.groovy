package deploydb.health

import com.codahale.metrics.health.HealthCheck
import com.codahale.metrics.health.HealthCheck.Result

/**
 *
 */
class WebhookHealthCheck extends HealthCheck {

    /**
     */
    @Override
    Result check() throws Exception {
        return Result.healthy()
    }
}
