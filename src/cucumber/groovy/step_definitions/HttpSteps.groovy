import cucumber.api.*

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.joda.time.DateTime


// Add functions to register hooks and steps to this script.
this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

When(~/^I GET "(.*?)" from the admin app$/) { String path ->
    response = getFromPath(path, true)
}

When(~/^I GET "(.*?)"$/) { String path ->
    response = getFromPath(path, false)
}

When(~/^I DELETE "(.*?)"$/) { String path ->
    response = deleteFromPath(path)
}

When(~/^I PUT to "(.*?)" with:$/) { String path, String requestBody ->
    response = putJsonToPath(path, requestBody)
}

When(~/^I PATCH "(.*?)" with:$/) { String path, String requestBody ->
    response = patchJsonToPath(path, requestBody)
}


Then(~/^the response should be (\d+)$/) { int statusCode ->
    assert response.status == statusCode
}

Then(~/^the response body should be:$/) { String expectedBody ->
    assert response.readEntity(String.class) == expectedBody
}

Then(~/^the body should be JSON:$/) { String expectedBody ->
    ObjectMapper mapper = new ObjectMapper()
    String body = response.readEntity(String.class)
    templateVariables = [
        'created_timestamp' : DateTime.now(),
    ]
    expectedBody = processTemplate(expectedBody, templateVariables)

    JsonNode expectedNode = mapper.readTree(expectedBody)
    JsonNode bodyNode = mapper.readTree(body)

    assert bodyNode == expectedNode
}
