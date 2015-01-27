package deploydb.resources

import com.codahale.metrics.annotation.Timed
import io.dropwizard.jersey.*
import io.dropwizard.jersey.params.*
import io.dropwizard.hibernate.UnitOfWork

import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.Context
import javax.ws.rs.core.Response
import java.util.concurrent.TimeUnit

import deploydb.dao.ArtifactDAO
import deploydb.dao.DeploymentDAO
import deploydb.models.Artifact
import deploydb.models.Deployment
import deploydb.mappers.DeploymentMapper

@Path("/api/v1/deployments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DeploymentResource {
    private final DeploymentDAO dao
    private final ArtifactDAO adao

    public DeploymentResource(DeploymentDAO dao, ArtifactDAO adao) {
        this.dao = dao
        this.adao = adao
    }

    @PUT
    @UnitOfWork
    @Timed(name='put-requests')
    public Response createDeployment(DeploymentMapper deploy) {
        Artifact artifact = this.adao.get(deploy.artifactId)

        if (artifact == null) {
            /** TODO: handle null */
        }

        Deployment created = this.dao.persist(new Deployment(artifact, deploy.environment))


        return Response.status(201).entity(created).build()
    }

    @PATCH
    @Path('{id}')
    @UnitOfWork
    @Timed(name='patch-requests')
    @Consumes('application/json-patch')
    public Deployment modifyDeployment(@PathParam('id') LongParam deploymentId, String changes) {
        println "CHANGES: ${changes}"
        return null
    }

    @GET
    @Path("{id}")
    @UnitOfWork
    @Timed(name = "get-requests")
    public Deployment byIdentifier(@PathParam("id") LongParam deploymentId) {
        Deployment deploy = this.dao.get(deploymentId.get())

        if (deploy == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND)
        }

        return deploy
    }

}
