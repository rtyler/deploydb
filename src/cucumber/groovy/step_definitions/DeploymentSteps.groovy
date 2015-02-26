this.metaClass.mixin(cucumber.api.groovy.EN)

import deploydb.dao.ArtifactDAO
import deploydb.dao.DeploymentDAO
import deploydb.dao.PromotionResultDAO
import deploydb.models.Artifact
import deploydb.models.Deployment
import deploydb.models.PromotionResult

Given(~/^there is a deployment$/) { ->
    withSession {
        ArtifactDAO adao = new ArtifactDAO(sessionFactory)
        Artifact a1 = sampleArtifact()

        PromotionResultDAO pdao = new PromotionResultDAO(sessionFactory)
        PromotionResult p1 = new PromotionResult("jenkins-smoke", NOT_STARTED, "")
        PromotionResult p2 = new PromotionResult("status-check", NOT_STARTED, "")

        DeploymentDAO dao = new DeploymentDAO(sessionFactory)
        Deployment d1 = new Deployment(adao.persist(a1),
            "pre-prod",
            "faas",
            "NOT_STARTED",
            [pdao.persist(p1), pdao.persist(p2)])
        dao.persist(d1)
    }
}

Given(~/^there are deployments$/) { ->
    withSession {
        ArtifactDAO adao = new ArtifactDAO(sessionFactory)
        Artifact a1 = sampleArtifact()
        Artifact a2 = sampleArtifact2()

        PromotionResultDAO pdao = new PromotionResultDAO(sessionFactory)
        PromotionResult p1 = new PromotionResult("jenkins-smoke", NOT_STARTED, "")
        PromotionResult p2 = new PromotionResult("status-check", NOT_STARTED, "")
        PromotionResult p3 = new PromotionResult("jenkins-smoke", NOT_STARTED, "")
        PromotionResult p4 = new PromotionResult("status-check", NOT_STARTED, "")

        DeploymentDAO dao = new DeploymentDAO(sessionFactory)
        Deployment d1 = new Deployment(adao.persist(a1),
                "pre-prod",
                "faas",
                "NOT_STARTED",
                [pdao.persist(p1), pdao.persist(p2)])
        dao.persist(d1)
        Deployment d2 = new Deployment(adao.persist(a2),
                "pre-prod",
                "faas",
                "NOT_STARTED",
                [pdao.persist(p3), pdao.persist(p4)])
        dao.persist(d2)
    }
}
