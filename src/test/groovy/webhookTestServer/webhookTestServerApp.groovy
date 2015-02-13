package webhookTestServer

import webhookTestServer.models.*
import webhookTestServer.health.*
import webhookTestServer.resources.*

import com.google.common.collect.ImmutableList

import io.dropwizard.Application
import io.dropwizard.setup.Environment

/**
 *
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

        environment.jersey().register(new RootResource())
        environment.jersey().register(new TestCallBackobjectResource())
    }
}