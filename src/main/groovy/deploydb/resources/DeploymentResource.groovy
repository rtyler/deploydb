package deploydb.resources

import com.codahale.metrics.annotation.Timed
import io.dropwizard.jersey.params.LongParam
import io.dropwizard.jersey.PATCH
import io.dropwizard.hibernate.UnitOfWork

import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.PathParam
import javax.ws.rs.WebApplicationException
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

import deploydb.dao.ArtifactDAO
import deploydb.dao.DeploymentDAO
import deploydb.models.Deployment

@Path("/api/deployments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DeploymentResource {
    private final DeploymentDAO dao
    private final ArtifactDAO adao

    DeploymentResource(DeploymentDAO dao, ArtifactDAO adao) {
        this.dao = dao
        this.adao = adao
    }

    /**
     * WIP: Not implemented yet, see:
     * <https://github.com/lookout/deploydb/issues/50>
     */
    @GET
    @Timed(name='get-requests')
    List<Deployment> fetchDeployments() {
        return []
    }

    @PATCH
    @Path('{id}')
    @UnitOfWork
    @Timed(name='patch-requests')
    @Consumes('application/json-patch')
    Deployment modifyDeployment(@PathParam('id') LongParam deploymentId, String changes) {
        Deployment deploy = this.dao.get(deploymentId.get())

        if (deploy == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND)
        }
        println "CHANGES: ${changes}"
        return deploy
    }

    @GET
    @Path("{id}")
    @UnitOfWork
    @Timed(name = "get-requests")
    Deployment byIdentifier(@PathParam("id") LongParam deploymentId) {
        Deployment deploy = this.dao.get(deploymentId.get())

        if (deploy == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND)
        }

        return deploy
    }

}
