package deploydb.models


import spock.lang.*
import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.configuration.ConfigurationParsingException
import io.dropwizard.configuration.ConfigurationValidationException
import deploydb.ModelLoader
import deploydb.registry.ModelRegistry
import deploydb.models.Webhook.Webhook
import deploydb.models.Webhook.Deployment

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class WebhookSpec extends Specification {
    def "ensure Webhook object can be instantiated"() {
        when:
        def webhook = new Webhook()

        then:
        webhook instanceof Webhook
    }
}

class WebhookWithArgsSpec extends Specification {

    private final ModelLoader<Webhook> webhookModelLoader =
            new ModelLoader<Webhook>(Webhook.class)

    def "Loading of valid webhook config file succeeds"() {
        given:
        Webhook webhook = webhookModelLoader.load('webhooks/test-valid.yml')

        expect:
        webhook.deployment.created == ['http://jenkins.example.com/job/notify-deployment-created/build']
        webhook.deployment.started == ['http://jenkins.example.com/job/notify-deployment-started/build']
        webhook.deployment.completed == ['http://jenkins.example.com/job/notify-deployment-completed/build']
        webhook.deployment.verified == ['http://jenkins.example.com/job/notify-deployment-verified/build']
        webhook.promotion.completed == ['http://jenkins.example.com/job/notify-promotion-completed/build']
    }

    def "Loading an empty webhook throws parsing exception"() {
        when:
        Webhook webhook = webhookModelLoader.load('webhooks/test-empty.yml')

        then:
        thrown(ConfigurationParsingException)
    }

    def "Loading of empty models webhook config file succeeds"() {
        given:
        Webhook webhook = webhookModelLoader.load('webhooks/test-emptymodels.yml')

        expect:
        webhook.deployment.created[] == []
        webhook.deployment.started[] == []
        webhook.deployment.completed[] == []
        webhook.promotion.completed[] == []
    }
    def "Loading a malformed webhook config file throws throws a parsing exception"() {
        when:
        Webhook webhook = webhookModelLoader.load('webhooks/test-malformed.yml')

        then:
        thrown(ConfigurationParsingException)
    }
    def "Loading a invalid webhook config file throws throws a parsing exception"() {
        when:
        Webhook webhook = webhookModelLoader.load('webhooks/test-invalid.yml')

        then:
        thrown(ConfigurationParsingException)
    }

    def "Loading a valid webhook with configuration inline succeeds"(){
        given:
        Webhook webhook = webhookModelLoader.loadFromString("deployment:\n" +
                "  created:\n" +
                "    - http://jenkins.example.com/job/notify-deployment-created/build\n" +
                "    - http://jenkins_prod.example.com/job/notify-deployment-created/build")
        expect:
        webhook.deployment.created == ['http://jenkins.example.com/job/notify-deployment-created/build',
                                       'http://jenkins_prod.example.com/job/notify-deployment-created/build']
    }
}
