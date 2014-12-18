import cucumber.api.*

// Add functions to register hooks and steps to this script.
this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

When(~/^I GET "(.*?)"$/) { String arg1 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}

Then(~/^the response should be (\d+)$/) { int arg1 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}

Then(~/^the response body should include:$/) { String arg1 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}

Then(~/^I should receive the JSON:$/) { String arg1 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}

