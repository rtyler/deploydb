package deploydb.resources

import com.codahale.metrics.annotation.Timed
import deploydb.mappers.DeploymentUpdateMapper
import deploydb.dao.DeploymentDAO
import deploydb.models.Deployment
import io.dropwizard.jersey.params.IntParam
import io.dropwizard.jersey.params.LongParam
import io.dropwizard.jersey.PATCH
import io.dropwizard.hibernate.UnitOfWork
import javax.validation.Valid
import javax.ws.rs.Consumes
import javax.ws.rs.DefaultValue
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.PathParam
import javax.ws.rs.QueryParam
import javax.ws.rs.WebApplicationException
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response


@Path("/api/deployments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(['application/json', 'application/vnd.deploydb.v1+json'])
public class DeploymentResource {
    private final DeploymentDAO dao

    DeploymentResource(DeploymentDAO dao) {
        this.dao = dao
    }

    /**
     * Returns all Deployment objects (paginate info is optional)
     */
    @GET
    @UnitOfWork
    @Timed(name = "get-requests")
    List<Deployment> getAll(
            @QueryParam("pageNumber") @DefaultValue("0") IntParam pageNumber,
            @QueryParam("perPageSize") @DefaultValue("20") IntParam perPageSize) {

        /**
         * Fetch deployment by page
         */
        List<Deployment> deployTable = this.dao.getByPage(pageNumber.get(), perPageSize.get())
        if (deployTable.isEmpty()) {
            throw new WebApplicationException(Response.Status.NOT_FOUND)
        }

        return deployTable
    }

    /**
     * Returns a Deployment object
     */
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

    /**
     * Returns the latest Deployment object
     */
    @GET
    @Path("latest")
    @UnitOfWork
    @Timed(name = "get-requests")
    Deployment getLatest() {
        Deployment deploy = this.dao.getLatest()

        if (deploy == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND)
        }

        return deploy
    }

    /**
     * Patch the Deployment object
     */
    @PATCH
    @Path('{id}')
    @UnitOfWork
    @Timed(name='patch-requests')
    void updateDeployment(@PathParam('id') LongParam deploymentId,
                          @Valid DeploymentUpdateMapper deploymentUpdateMapper) {
        Deployment deploy = this.dao.get(deploymentId.get())

        if (deploy == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND)
        }

        /**
         *  FIXME: valid deploymentUpdateMapper.status are (Started, Completed, Failed)
         */
        /*
        if (deploymentUpdateMapper.status != deploy.status) {
            FIXME: Take Actions
        }
        */
    }
}
