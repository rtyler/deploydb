package deploydb.resources

import com.codahale.metrics.annotation.Timed
import io.dropwizard.jersey.params.*
import io.dropwizard.hibernate.UnitOfWork

import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.Context
import javax.ws.rs.core.Response
import java.util.concurrent.TimeUnit

import deploydb.dao.DeploymentDAO
import deploydb.models.Deployment

@Path("/api/v1/deployments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DeploymentResource {
    private final DeploymentDAO dao

    public DeploymentResource(DeploymentDAO dao) {
        this.dao = dao
    }

    @GET
    @Path("{id}")
    @UnitOfWork
    @Timed(name = "get-requests")
    public Deployment byIdentifier(@Context HttpHeaders headers,
                                   @PathParam("id") LongParam deploymentId) {
        Deployment deploy = this.dao.get(deploymentId.get())

        if (deploy == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND)
        }

        return deploy
    }

}
