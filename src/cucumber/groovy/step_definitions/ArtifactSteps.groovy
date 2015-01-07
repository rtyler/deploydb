import cucumber.api.*

this.metaClass.mixin(cucumber.api.groovy.EN)

import deploydb.dao.ArtifactDAO
import deploydb.models.Artifact

Given(~/^there is an artifact$/) { ->
    withSession {
        ArtifactDAO dao = new ArtifactDAO(sessionFactory)
        Artifact a = sampleArtifact()
        a.sourceUrl = 'http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.1/cucumber-artifact-1.0.1.jar'
        dao.create(a)
    }
}
