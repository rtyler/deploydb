package webhookTestServer

import io.dropwizard.setup.Bootstrap
import webhookTestServer.models.RequestWebhookObject
import webhookTestServer.models.ResponseWebhookObject
import webhookTestServer.health.webhookTestServerHealthCheck

import com.google.common.collect.ImmutableList

import io.dropwizard.Application
import io.dropwizard.setup.Environment

/**
 *
 */
class webhookTestServerApp extends Application<webhookTestServerConfiguration>{
//    private final ImmutableList models = ImmutableList.of(RequestWebhookObject, ResponseWebhookObject)
    private RequestWebhookObject requestWebhookObject
    private ResponseWebhookObject responseWebhookObject

    static void main(String[] args) throws Exception {
        new webhookTestServerApp().run(args)
    }

    @Override
    String getName() {
        return 'webhookTestServer'
    }

    @Override
    public void initialize(Bootstrap<webhookTestServerConfiguration> bootstrap) {
        requestWebhookObject = new RequestWebhookObject()
        responseWebhookObject = new ResponseWebhookObject()
    }

    @Override
    public void run(webhookTestServerConfiguration configuration,
                    Environment environment) {

        environment.healthChecks().register('sanity', new webhookTestServerHealthCheck())

        /**
         * FIXME: remove this after testing
         */
        responseWebhookObject.responseCode = "201"
        responseWebhookObject.delayBeforeResponseInSecs = 0

        environment.jersey().register(new resources.RootResource())
        environment.jersey().register(new resources.TestCallBackobjectResource(requestWebhookObject,
                responseWebhookObject))

    }
}
