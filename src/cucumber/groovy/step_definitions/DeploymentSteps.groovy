this.metaClass.mixin(cucumber.api.groovy.EN)


import deploydb.dao.ArtifactDAO
import deploydb.dao.DeploymentDAO
import deploydb.models.Artifact
import deploydb.models.Deployment
import deploydb.models.PromotionResult
import deploydb.Status


Given(~/^there is a deployment$/) { ->
    withSession {

        /**
         * Create sample artifact
         */
        ArtifactDAO adao = new ArtifactDAO(sessionFactory)
        Artifact a1 = sampleArtifact()
        adao.persist(a1)

        /**
         * Create sample promotionResult(s)
         */
        PromotionResult p1 = new PromotionResult("jenkins-smoke", Status.STARTED, null)

        /**
         * Create deployment
         */
        Deployment d1 = new Deployment(a1,
                "pre-prod",
                "faas",
                Status.STARTED)
        d1.addPromotionResult(p1)

        /**
         * Save deployment in DB, which will save the promotionResults as well
         */
        DeploymentDAO dao = new DeploymentDAO(sessionFactory)
        dao.persist(d1)
    }
}

Given(~/^there are deployments$/) { ->
    withSession {

        /**
         * Create sample artifact
         */
        ArtifactDAO adao = new ArtifactDAO(sessionFactory)
        Artifact a1 = sampleArtifact()
        Artifact a2 = sampleArtifactV2()
        adao.persist(a1)
        adao.persist(a2)

        /**
         * Create sample promotionResult(s)
         */
        PromotionResult p1 = new PromotionResult("jenkins-smoke", Status.STARTED, null)
        PromotionResult p2 = new PromotionResult("status-check", Status.STARTED,
                "http://local.lookout.com/jenkins/job-id/2/results")

        /**
         * Create deployment
         */
        Deployment d1 = new Deployment(adao.persist(a1),
                "pre-prod",
                "faas",
                Status.STARTED)
        d1.addPromotionResult(p1)
        Deployment d2 = new Deployment(adao.persist(a2),
                "pre-prod",
                "faas",
                Status.STARTED)
        d2.addPromotionResult(p2)

        /**
         * Save deployment in DB, which will save the promotionResults as well
         */
        DeploymentDAO dao = new DeploymentDAO(sessionFactory)
        dao.persist(d1)
        dao.persist(d2)
    }
}
