import cucumber.api.*

this.metaClass.mixin(cucumber.api.groovy.EN)

import deploydb.dao.ArtifactDAO
import deploydb.models.Artifact

Given(~/^there is an artifact$/) { ->
    withSession {
        ArtifactDAO dao = new ArtifactDAO(sessionFactory)
        dao.create(new Artifact('com.example.cucumber',
                                'cucumber-artifact'))
    }
}

Given(~/^there is an artifact named "(.*?)"$/) { String artifactName ->
    // NOTE: no-op for now
}
