package webhookTestServer

import io.dropwizard.setup.Bootstrap
import webhookTestServer.models.ReceivedWebhookObject
import webhookTestServer.models.ResponseWebhookObject
import webhookTestServer.health.webhookTestServerHealthCheck

import com.google.common.collect.ImmutableList

import io.dropwizard.Application
import io.dropwizard.setup.Environment

/**
 *
 */
class webhookTestServerApp extends Application<webhookTestServerConfiguration>{
//    private final ImmutableList models = ImmutableList.of(ReceivedWebhookObject, ResponseWebhookObject)
    private ReceivedWebhookObject receivedWebhookObject
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
        receivedWebhookObject = new ReceivedWebhookObject()
        responseWebhookObject = new ResponseWebhookObject()
    }

    @Override
    public void run(webhookTestServerConfiguration configuration,
                    Environment environment) {

        environment.healthChecks().register('sanity', new webhookTestServerHealthCheck())

        /**
         * remove this after testing
         */
        receivedWebhookObject.setConfiguredUrl("/job/notify-deployment-started/build")
        responseWebhookObject.responseCode = "201"
        responseWebhookObject.delayBeforeResponseInSecs = 0

        environment.jersey().register(new resources.RootResource())
        environment.jersey().register(new resources.TestCallBackobjectResource(receivedWebhookObject,
                responseWebhookObject))

        /**
         * new TestCallbackObject(
         "/job/notify-deployment-started/build", "POST", "200", "", 0))
         */
    }
}
