package webhookTestServer.health


import com.codahale.metrics.health.HealthCheck
import com.codahale.metrics.health.HealthCheck.Result



/**
 *
 */
class webhookTestServerHealthCheck extends HealthCheck {

    /**
     */
    @Override
    Result check() throws Exception {
        return Result.healthy()
    }
}
