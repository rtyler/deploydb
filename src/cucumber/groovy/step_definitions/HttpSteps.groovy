import cucumber.api.*

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.joda.time.DateTime
import java.util.Random 

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

When(~/^I PUT to "(.*?)" with a (.*?) over ([1-9][0-9]*) characters$/) { String path, String var, int varSize ->

    // Create a randomString of size varSize+100 
    String alphabet = (('a'..'z') + ('A'..'Z')+('0'..'9')).join()
    String randomString = ""
    for ( i in 1..varSize+100 ) {
        randomString += alphabet[ new Random().nextInt(alphabet.length()-1)]
    }

    print "MVK : $var\n"

    group = var == "group" ? randomString : "com.example.cucumber"
    name  = var == "name" ? randomString : "cukes"
    version = var == "version" ? randomString : "1.2.345"
    sourceUrl = var == "sourceUrl" ? randomString : "http://example.com/cucumber.jar"
    
    requestBody = """ {
        "group" : "$group",
        "name" : "$name",
        "version" : "$version",
        "sourceUrl" : "$sourceUrl"
      }
    """

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
