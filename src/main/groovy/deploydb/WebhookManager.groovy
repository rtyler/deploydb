package deploydb

import com.github.lookout.whoas.HookRequest
import deploydb.models.Webhook.Webhook

import io.dropwizard.lifecycle.Managed

import com.github.lookout.whoas.InMemoryQueue
import com.github.lookout.whoas.SequentialHookRunner
import com.github.lookout.whoas.AbstractHookRunner
import com.github.lookout.whoas.AbstractHookQueue

import org.slf4j.Logger
import org.slf4j.LoggerFactory


class WebhookManager implements Managed {
    private Thread runnerThread
    private SequentialHookRunner runner
    private InMemoryQueue queue = new InMemoryQueue()
    private final Logger logger = LoggerFactory.getLogger(WebhookManager.class)
    private Webhook webhook

    WebhookManager( ) {
        runner = new SequentialHookRunner(this.queue)


        /*
         * Instantiate the loader to load the webhook object from
         * parsed object
         */
        webhook = new Webhook()

        runnerThread = new Thread({
            runner.run()
        })
        runnerThread.name = 'WebhookManager Thread'
        logger.info("Webhook runner thread created: ${runnerThread}")
    }

    void loadConfiguration(String fileName) {
        ModelLoader<Webhook> webhookLoader = new
                ModelLoader<Webhook>(models.Webhook.Webhook.class)
        webhook = webhookLoader.load(fileName)
    }

    void loadConfigurationFromString(String configData) {
        ModelLoader<Webhook> webhookLoader = new
            ModelLoader<Webhook>(models.Webhook.Webhook.class)
        webhook = webhookLoader.loadFromString(configData)
    }

    @Override
    void start() {
        runnerThread.start()
        logger.info("${runnerThread} started")
    }

    @Override
    void stop() {
        runner.stop()
        logger.info("${runnerThread} stopped")
    }

    boolean createDeploymentWebhook(WebhookModelMapper webhookModelMapper, String eventType)
    {
        String urlName = webhook.deployment.created[0]
        HookRequest hookRequest = new HookRequest(urlName,
                webhookModelMapper.toPayload())

        String urlPayload = webhookModelMapper.toPayload()
        return queue.push(hookRequest)
    }
    /**
     * Return true if the webhook thread is running
     */
    Boolean getRunning() {
        return runnerThread.isAlive()
    }

    AbstractHookRunner getRunner() {
        return this.runner
    }

    AbstractHookQueue getQueue() {
        return this.queue
    }
}
