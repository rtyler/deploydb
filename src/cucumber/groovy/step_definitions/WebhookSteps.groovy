this.metaClass.mixin(cucumber.api.groovy.EN)


import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import deploydb.WebhookManager
import org.joda.time.DateTime
import webhookTestServer.models.RequestWebhookObject

Given(~/^the webhooks configuration:$/) { String configBody ->
    withWebhookManager { WebhookManager webhookManager ->
        webhookManager.loadConfigurationFromString(configBody)
   }
}

When (~/^I POST to "(.*?)" with an artifact/) { String path ->
    String requestBody = """{
"group" : "com.example.cucumber",
"name" : "cucumber-artifact",
"version" : "1.0.1",
"sourceUrl" : "http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.1/cucumber-artifact-1.0.1.jar"
}"""

    response = postJsonToPath(path, requestBody)
}

Then(~/^the webhook should be invoked with the JSON:$/) { String expectedMessageBody ->
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

And (~/^Wait for (\d+) seconds/) { int secondsToWait ->
    sleep(secondsToWait * 1000)
}

When(~/I trigger deployment PATCH "(.*?)" with:$/) { String path ->
    response = postJsonToPath(path, requestBody)
}