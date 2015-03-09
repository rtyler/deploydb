this.metaClass.mixin(cucumber.api.groovy.EN)


import com.fasterxml.jackson.core.JsonToken
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import deploydb.ModelLoader
import deploydb.Status
import deploydb.dao.ArtifactDAO
import deploydb.dao.FlowDAO
import deploydb.models.Artifact
import deploydb.models.Deployment
import deploydb.models.Environment
import deploydb.models.Flow
import deploydb.models.PromotionResult
import deploydb.registry.ModelRegistry
import org.joda.time.DateTime


import deploydb.WebhookManager
import webhookTestServer.models.RequestWebhookObject


Given(~/^a webhook "(.*?)" configuration:$/) { String eventType, String configBody ->

    List<String> paths = getUrlPathFromWebhookConfigBody(configBody, eventType)

    /*
     * Our request object deals with only one webhook request at a time, once we allow
     * multiple requests, then we can save the array in the RequestWebhook object
     */
    withWebhookManager { WebhookManager webhookManager, RequestWebhookObject requestWebhookObject ->
        requestWebhookObject.setConfiguredUriPath(paths?paths[0] : "")
        webhookManager.loadConfigurationFromString(configBody)
   }
}

Given(~/^an environment webhook "(.*?)" configuration named "(.*?)":$/) {String eventType,
                                                                         String envIdent, String configBody ->

    ModelLoader<Environment> environmentLoader = new ModelLoader<>(Environment.class)
    Environment a = environmentLoader.loadFromString(configBody)

    List<String> paths = getUrlPathFromWebhook(a.webhooks, configBody, eventType)

    /*
    * Our request object deals with only one webhook request at a time, once we allow
    * multiple requests, then we can save the array in the RequestWebhook object
    */   withWebhookManager { WebhookManager webhookManager, RequestWebhookObject requestWebhookObject ->
        requestWebhookObject.setConfiguredUriPath(paths?paths[0] : "")
    }

    withEnvironmentRegistry { ModelRegistry<Environment> environmentRegistry ->
        a.ident = envIdent
        environmentRegistry.put(envIdent, a)
    }


}

When (~/^I POST to "(.*?)" with an artifact/) { String path ->
    String requestBody = """{
"group" : "com.example.cucumber",
"name" : "cukes",
"version" : "1.0.1",
"sourceUrl" : "http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.1/cucumber-artifact-1.0.1.jar"
}"""

    response = postJsonToPath(path, requestBody)
}

Then(~/^the webhook should be invoked with the JSON:$/) { String expectedMessageBody ->
    sleep(1000)
    withRequestWebhookObject { RequestWebhookObject requestWebhookObject ->
        ObjectMapper mapper = new ObjectMapper()

        String requestMessageBody = requestWebhookObject.getRequestMessageBody()

        templateVariables = [
                'created_timestamp' : DateTime.now(),
        ]
        expectedMessageBody = processTemplate(expectedMessageBody, templateVariables)

        JsonNode expectedNode = mapper.readTree(expectedMessageBody)
        JsonNode requestNode = mapper.readTree(requestMessageBody)

        assert expectedNode == requestNode
    }
}


When(~/I trigger deployment PATCH with:$/) { String path ->
    response = postJsonToPath(path, requestBody)
}

And(~/there is a deployment in "(.*?)" state$/) { String deploymentState ->

    withSession {

        /**
         * Create sample artifact
         */
        ArtifactDAO adao = new ArtifactDAO(sessionFactory)
        Artifact a1 = sampleArtifact()
        adao.persist(a1)

        /**
         * Create sample promotionResult(s)
         */
        PromotionResult p1 = new PromotionResult("jenkins-smoke", Status.STARTED, null)
        WebhookManager webhookManager = new WebhookManager()
        /**
         * Create deployment
         */
        Deployment d1 = new Deployment(a1,
                "pre-prod",
                "faas",
                webhookManager.getMemberOfObject(Status, deploymentState))
        d1.addPromotionResult(p1)

        /* Create a flow */
        Flow f = new Flow(a1, "faas")
        f.addDeployment(d1)

        /**
         * Save flow in DB, which will save the deployments & promotionResults as well
         */
        FlowDAO fdao = new FlowDAO(sessionFactory)
        fdao.persist(f)
    }

}