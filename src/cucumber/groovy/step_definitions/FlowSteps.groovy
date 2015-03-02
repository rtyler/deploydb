this.metaClass.mixin(cucumber.api.groovy.EN)

import deploydb.dao.ArtifactDAO
import deploydb.dao.DeploymentDAO
import deploydb.dao.FlowDAO

import deploydb.models.Artifact
import deploydb.models.Deployment
import deploydb.models.Flow

Given(~/^there is a flow$/) { ->
    withSession {
        ArtifactDAO adao = new ArtifactDAO(sessionFactory)
        DeploymentDAO ddao = new DeploymentDAO(sessionFactory)
        FlowDAO fdao = new FlowDAO(sessionFactory)

        Artifact a = sampleArtifact()
        Deployment d = new Deployment(adao.persist(a), "pre-production")
        Deployment d1 = new Deployment(adao.persist(a), "production")

        Flow f = new Flow()
        f.setArtifact(a)
        f.setService("bluffdale")
        d.setFlow(f)
        d1.setFlow(f)

        Set<Deployment> hashSet = new HashSet<Deployment>()
        hashSet.add(ddao.persist(d))
        hashSet.add(ddao.persist(d1))

        f.setDeployments(hashSet)


        fdao.persist(f)
    }
}
