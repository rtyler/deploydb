import cucumber.api.*

this.metaClass.mixin(cucumber.api.groovy.EN)

import deploydb.dao.*
import deploydb.models.*

Given(~/^there is a deployment$/) { ->
    withSession {
        ArtifactDAO adao = new ArtifactDAO(sessionFactory)
        DeploymentDAO dao = new DeploymentDAO(sessionFactory)
        Artifact a = sampleArtifact()
        Deployment d = new Deployment(adao.create(a),
                                      "pre-production")
        dao.create(d)
    }
}

