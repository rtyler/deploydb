import cucumber.api.*

this.metaClass.mixin(cucumber.api.groovy.EN)


Given(~/^there is an artifact with ID (\d+)$/) { int artifactId ->
  throw new PendingException()
}

Given(~/^there is an artifact named "(.*?)"$/) { String artifactName ->
    // NOTE: no-op for now
}
