package webhookTestServer

import webhookTestServer.models.TestCallbackObject
import webhookTestServer.health.webhookTestServerHealthCheck

import com.google.common.collect.ImmutableList

import io.dropwizard.Application
import io.dropwizard.setup.Environment

/**
 * Class for WebhookTestServer. Based on the configuration, this class
 * will send appropriate responses
 */
class webhookTestServerApp extends Application<webhookTestServerConfiguration>{
    private final ImmutableList models = ImmutableList.of(TestCallbackObject)

    static void main(String[] args) throws Exception {
        new webhookTestServerApp().run(args)
    }

    @Override
    String getName() {
        return 'webhookTestServer'
    }

    @Override
    public void run(webhookTestServerConfiguration configuration,
                    Environment environment) {

        environment.healthChecks().register('sanity', new webhookTestServerHealthCheck())

        environment.jersey().register(new resources.RootResource())
        environment.jersey().register(new resources.TestCallBackobjectResource())
    }
}
