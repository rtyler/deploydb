import cucumber.api.*

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper

// Add functions to register hooks and steps to this script.
this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

When(~/^I GET "(.*?)" from the admin app$/) { String path ->
    response = getFromPath(path, true)
}

When(~/^I GET "(.*?)"$/) { String path ->
    response = getFromPath(path, false)
}

Then(~/^the response should be (\d+)$/) { int statusCode ->
    assert response.status == statusCode
}

Then(~/^the response body should be:$/) { String expectedBody ->
    assert response.getEntity(String.class) == expectedBody
}

Then(~/^the body should be JSON:$/) { String expectedBody ->
    ObjectMapper mapper = new ObjectMapper()
    String body = response.getEntity(String.class)
    JsonNode expectedNode = mapper.readTree(expectedBody)
    JsonNode bodyNode = mapper.readTree(body)

    assert bodyNode == expectedNode
}
