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
    private Webhook webhook = null

    /*
     * This function will be used to fetch different webhooks types
     * stored webhook object given the name of the type - "created",
     * "started", "completed".
     */
    def getMemberOfObject(klass, variable) {
        return klass."$variable"
    }

    WebhookManager( ) {
        runner = new SequentialHookRunner(this.queue)

        runnerThread = new Thread({
            runner.run()
        })
        runnerThread.name = 'WebhookManager Thread'
        logger.info("Webhook runner thread created: ${runnerThread}")
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

    boolean sendDeploymentWebhook( String eventType, Webhook environmentWebhook,
                                   WebhookModelMapper webhookModelMapper) {
        /*
         *  Initialize the list for URL's configured in webhooks
         */
        List<String> eventUrlList = []

        /*
         * Get all the configured webhooks - global + environment
         */
        if (environmentWebhook != null) {
            eventUrlList = environmentWebhook.deployment ?
                    getMemberOfObject(environmentWebhook.deployment, eventType) : []
        }
        if (webhook != null) {
            eventUrlList += webhook.deployment ?
                    getMemberOfObject(webhook.deployment, eventType) : []
        }

        /*
         * If the URL list is non empty, iterate over URLs and send the webhook request
         */
        eventUrlList? eventUrlList.any() { urlName ->
            HookRequest hookRequest = new HookRequest(urlName,
                    webhookModelMapper.toPayload())

            queue.push(hookRequest)
        } : true
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
