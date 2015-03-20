package deploydb

import com.github.lookout.whoas.HookRequest
import com.github.lookout.whoas.InMemoryQueue
import deploydb.mappers.DeploymentWebhookMapper
import spock.lang.*

class WebhookManagerSpec extends Specification {

    def "ensure constructor works"() {
        given:
        WebhookManager m

        when:
        m = new WebhookManager()

        then:
        m instanceof WebhookManager
    }

    def "running() should return false by default"() {
        given:
        WebhookManager m = new WebhookManager()

        expect:
        m.running == false
    }

    def "sendDeploymentWebhook() should return true if no webhooks are configured"() {
        given:
        WebhookManager m = new WebhookManager()

        expect:
        m.sendDeploymentWebhook("created", null, null) == true
    }

    def "sendDeploymentWebhook() should push correct hook requests when global webhooks are configured"() {

        given:
        deploydb.models.Webhook.Deployment webhookDeployment =
                new deploydb.models.Webhook.Deployment([],
                        ["http://www.localhost.com/test-build", "http://www.localhost.com/test1-build"],
                        [], [])
        WebhookManager m = new WebhookManager()
        m.queue = new InMemoryQueue()
        m.webhook = new deploydb.models.Webhook.Webhook(webhookDeployment, null)

        // mock the queue push
        int counter = 0
        m.queue.metaClass.push = {HookRequest hookRequest ->
            counter++
            true
        }

        when:
        m.sendDeploymentWebhook("created", null, new DeploymentWebhookMapper())

        then:
        counter == 2
    }

    def "sendDeploymentWebhook() should push correct hook requests when environment webhooks are configured"() {

        given:
        deploydb.models.Webhook.Deployment webhookDeployment =
                new deploydb.models.Webhook.Deployment([],
                        ["http://www.localhost.com/test-build", "http://www.localhost.com/test1-build"],
                        [], [])
        WebhookManager m = new WebhookManager()
        m.queue = new InMemoryQueue()
        deploydb.models.Webhook.Webhook ewh = new deploydb.models.Webhook.Webhook(webhookDeployment, null)

        // mock the queue push
        int counter = 0
        m.queue.metaClass.push = {HookRequest hookRequest ->
            counter++
            true
        }

        when:
        m.sendDeploymentWebhook("created", ewh, new DeploymentWebhookMapper())

        then:
        counter == 2
    }

    def "sendDeploymentWebhook() should push correct hook requests when both global and environment webhooks are configured"() {

        given:
        deploydb.models.Webhook.Deployment webhookDeployment =
                new deploydb.models.Webhook.Deployment([],
                        ["http://www.localhost.com/test-build", "http://www.localhost.com/test1-build"],
                        [], [])
        WebhookManager m = new WebhookManager()
        m.queue = new InMemoryQueue()
        m.webhook = new deploydb.models.Webhook.Webhook(webhookDeployment, null)
        deploydb.models.Webhook.Webhook ewh = new deploydb.models.Webhook.Webhook(webhookDeployment, null)

        // mock the queue push
        int counter = 0
        m.queue.metaClass.push = {HookRequest hookRequest ->
            counter++
            true
        }

        when:
        m.sendDeploymentWebhook("created", ewh, new DeploymentWebhookMapper())

        then:
        counter == 4
    }

    def "sendPromotionWebhook() should return true if no webhooks are configured"() {
        given:
        WebhookManager m = new WebhookManager()

        expect:
        m.sendPromotionWebhook("completed", null, null) == true
    }

    def "sendPromotiontWebhook() should push correct hook requests when global webhooks are configured"() {

        given:
        deploydb.models.Webhook.Promotion webhookPromotion =
                new deploydb.models.Webhook.Promotion(
                        ["http://www.localhost.com/test-build", "http://www.localhost.com/test1-build"])
        WebhookManager m = new WebhookManager()
        m.queue = new InMemoryQueue()
        m.webhook = new deploydb.models.Webhook.Webhook(null, webhookPromotion)

        // mock the queue push
        int counter = 0
        m.queue.metaClass.push = {HookRequest hookRequest ->
            counter++
            true
        }

        when:
        m.sendPromotionWebhook("completed", null, new DeploymentWebhookMapper())

        then:
        counter == 2
    }

    def "sendPromotiontWebhook() should push correct hook requests when environment webhooks are configured"() {

        given:
        deploydb.models.Webhook.Promotion webhookPromotion =
                new deploydb.models.Webhook.Promotion(
                        ["http://www.localhost.com/test-build", "http://www.localhost.com/test1-build"])

        WebhookManager m = new WebhookManager()
        m.queue = new InMemoryQueue()
        deploydb.models.Webhook.Webhook ewh = new deploydb.models.Webhook.Webhook(null, webhookPromotion)

        // mock the queue push
        int counter = 0
        m.queue.metaClass.push = {HookRequest hookRequest ->
            counter++
            true
        }

        when:
        m.sendPromotionWebhook("completed", ewh, new DeploymentWebhookMapper())

        then:
        counter == 2
    }

    def "sendPromotiontWebhook() should push correct hook requests when  both global and environment webhooks are configured"() {

        given:
        deploydb.models.Webhook.Promotion webhookPromotion =
                new deploydb.models.Webhook.Promotion(
                        ["http://www.localhost.com/test-build", "http://www.localhost.com/test1-build"])

        WebhookManager m = new WebhookManager()
        m.queue = new InMemoryQueue()
        m.webhook = new deploydb.models.Webhook.Webhook(null, webhookPromotion)
        deploydb.models.Webhook.Webhook ewh = new deploydb.models.Webhook.Webhook(null, webhookPromotion)

        // mock the queue push
        int counter = 0
        m.queue.metaClass.push = {HookRequest hookRequest ->
            counter++
            true
        }

        when:
        m.sendPromotionWebhook("completed", ewh, new DeploymentWebhookMapper())

        then:
        counter == 4
    }
}
