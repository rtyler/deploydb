this.metaClass.mixin(cucumber.api.groovy.EN)


import com.fasterxml.jackson.core.JsonToken
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import deploydb.ModelLoader
import deploydb.models.Environment
import deploydb.registry.ModelRegistry
import org.joda.time.DateTime
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.core.JsonParser



import deploydb.WebhookManager
import webhookTestServer.models.RequestWebhookObject


Given(~/^a webhook "(.*?)" configuration:$/) { String eventType, String configBody ->

    String path = getUrlPathFromConfigBody(configBody, eventType)

    withWebhookManager { WebhookManager webhookManager, RequestWebhookObject requestWebhookObject ->
        requestWebhookObject.setConfiguredUriPath(path)
        webhookManager.loadConfigurationFromString(configBody)
   }
}

Given(~/^an environment webhook "(.*?)" configuration named "(.*?)":$/) {String eventType, String envIdent, String configBody ->

    String path = getUrlPathFromConfigBody(configBody, eventType)
    withWebhookManager { WebhookManager webhookManager, RequestWebhookObject requestWebhookObject ->
        requestWebhookObject.setConfiguredUriPath(path)
    }

    withEnvironmentRegistry { ModelRegistry<Environment> environmentRegistry ->
        ModelLoader<Environment> environmentLoader = new ModelLoader<>(Environment.class)
        Environment a = environmentLoader.loadFromString(configBody)
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


When(~/I trigger deployment PATCH "(.*?)" with:$/) { String path ->
    response = postJsonToPath(path, requestBody)
}