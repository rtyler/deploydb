import cucumber.api.*

this.metaClass.mixin(cucumber.api.groovy.EN)

import deploydb.dao.ArtifactDAO
import deploydb.models.Artifact

Given(~/^there is an artifact$/) { ->
    withSession {
        ArtifactDAO dao = new ArtifactDAO(sessionFactory)
        Artifact a = sampleArtifact()
        dao.persist(a)
    }
}
Given(~/^there are artifacts$/) { ->
    withSession {
        ArtifactDAO dao = new ArtifactDAO(sessionFactory)
        Artifact a = sampleArtifact()
        Artifact a2 = sampleArtifactV2()        
        dao.persist(a)
        dao.persist(a2)
    }
}

Given(~/^I have an artifact request$/) { ->
}

And(~/^the (.*?) is over ([1-9][0-9]*) characters$/) { String var, int varSize ->

    // Create a randomString of size varSize+1
    String randomString = "test-".padRight(varSize+1, "a")

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
}
