import groovy.transform.InheritConstructors
import cucumber.api.*
import com.sun.jersey.api.client.*

import deploydb.*
import deploydb.cucumber.StubAppRunner

// Add functions to register hooks and steps to this script.
this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)


/*
 * Hooks to ensure that we're setting our test application up properly
 */
Before {
    app = new StubAppRunner(DeployDBApp.class, null)
    app.start()
    client = new Client()
}

After {
    app.stop()
}

/*******************************************************************************/

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


/**
 * Minor convenience method to make sure we're dispatching GET requests to the
 * right port in our test application
 */
ClientResponse getFromPath(String path, boolean isAdmin) {
    int port = isAdmin ? app.getAdminPort() : app.getLocalPort()

    return client.resource(String.format("http://localhost:%d${path}", port)).get(ClientResponse.class)
}
