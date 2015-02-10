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
