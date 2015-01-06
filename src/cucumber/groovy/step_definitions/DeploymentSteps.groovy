import cucumber.api.*

this.metaClass.mixin(cucumber.api.groovy.EN)

import deploydb.dao.DeploymentDAO
import deploydb.models.Deployment

Given(~/^there is a deployment$/) { ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}

