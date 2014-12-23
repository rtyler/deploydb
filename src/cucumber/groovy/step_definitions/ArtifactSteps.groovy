import cucumber.api.*

// Add functions to register hooks and steps to this script.
this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)


Given(~/^there is an artifact with ID (\d+)$/) { int artifactId ->
    // NOTE: no-op for now
}

Given(~/^there is an artifact named "(.*?)"$/) { String artifactName ->
    // NOTE: no-op for now
}
