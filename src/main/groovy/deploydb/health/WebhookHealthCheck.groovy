package deploydb.health

import deploydb.WebhookManager
import com.codahale.metrics.health.HealthCheck
import com.codahale.metrics.health.HealthCheck.Result

/**
 *
 */
class WebhookHealthCheck extends HealthCheck {
    private WebhookManager manager

    WebhookHealthCheck(WebhookManager hookManager) {
        this.manager = hookManager
    }

    /**
     */
    @Override
    Result check() throws Exception {
        if (manager.running) {
            return Result.healthy()
        }
        return Result.unhealthy('WebhookManager not running')
    }
}
