package deploydb.resources

import com.codahale.metrics.annotation.Timed
import deploydb.mappers.DeploymentUpdateMapper
import deploydb.models.Artifact
import io.dropwizard.jersey.params.IntParam
import io.dropwizard.jersey.params.LongParam
import io.dropwizard.jersey.PATCH
import io.dropwizard.hibernate.UnitOfWork

import javax.ws.rs.Consumes
import javax.ws.rs.DefaultValue
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.PathParam
import javax.ws.rs.QueryParam
import javax.ws.rs.WebApplicationException
import javax.ws.rs.core.Context
import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

import deploydb.dao.ArtifactDAO
import deploydb.dao.DeploymentDAO
import deploydb.models.Deployment

@Path("/api/deployments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(['application/json', 'application/vnd.deploydb.v1+json'])
public class DeploymentResource {
    private final DeploymentDAO dao
    private final ArtifactDAO adao

    DeploymentResource(DeploymentDAO dao, ArtifactDAO adao) {
        this.dao = dao
        this.adao = adao
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
         * Return empty list, if pageNumber and perPageSize is 0
         */
        if (pageNumber == 0 && perPageSize == 0) {
            return []
        }

        /**
         * Fetch deployment by page
         */
        List<Deployment> deployTable = this.dao.getByPage(pageNumber, perPageSize)

        if (deployTable.isEmpty()) {
            throw new WebApplicationException(Response.Status.NOT_FOUND)
        }

        /*
        return deployTable.collect() { Deployment deploy ->
            new DeploymentResponseMapper(deploy)
        }
        */
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

    @PATCH
    @Path('{id}')
    @UnitOfWork
    @Timed(name='patch-requests')
    @Consumes('application/json-patch')
    void updateDeployment(@PathParam('id') LongParam deploymentId,
                                DeploymentUpdateMapper deploymentUpdateMapper ) {
        Deployment deploy = this.dao.get(deploymentId.get())

        if (deploy == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND)
        }

        /**
         *  FIXME: valid deploymentUpdateMapper.status are (Started, Completed, Failed)
         */
        if (deploymentUpdateMapper.status != deploy.status) {
            /* FIXME: Take Actions */
        }
    }
}
