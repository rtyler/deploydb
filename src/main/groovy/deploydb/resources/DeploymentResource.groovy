package deploydb.resources

import com.codahale.metrics.annotation.Timed
import deploydb.Status
import deploydb.mappers.DeploymentUpdateMapper
import deploydb.dao.DeploymentDAO
import deploydb.mappers.PromotionResultAddMapper
import deploydb.models.Deployment
import deploydb.models.PromotionResult
import io.dropwizard.jersey.params.IntParam
import io.dropwizard.jersey.params.LongParam
import io.dropwizard.jersey.PATCH
import io.dropwizard.hibernate.UnitOfWork
import org.apache.commons.lang3.tuple.Pair

import javax.validation.Valid
import javax.ws.rs.Consumes
import javax.ws.rs.DefaultValue
import javax.ws.rs.GET
import javax.ws.rs.POST
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
    Set<Pair<Status, Status>> deploymentStatusTransitionPairs
    Set<Pair<Status, Status>> promotionResultStatusTransitionPairs

    DeploymentResource(DeploymentDAO dao) {
        this.dao = dao

        /**
         *  Valid State transitions for deployment are:
         *  NOT_STARTED -> STARTED
         *  NOT_STARTED -> COMPLETED
         *  NOT_STARTED -> FAILED
         *  STARTED -> COMPLETED
         *  STARTED -> FAILED
         */
        deploymentStatusTransitionPairs = new HashSet<>()
        deploymentStatusTransitionPairs.add(Pair.of(Status.NOT_STARTED, Status.STARTED))
        deploymentStatusTransitionPairs.add(Pair.of(Status.NOT_STARTED, Status.COMPLETED))
        deploymentStatusTransitionPairs.add(Pair.of(Status.NOT_STARTED, Status.FAILED))
        deploymentStatusTransitionPairs.add(Pair.of(Status.STARTED, Status.COMPLETED))
        deploymentStatusTransitionPairs.add(Pair.of(Status.STARTED, Status.FAILED))

        /**
         *  Valid State transitions for PromotionResult are:
         *  STARTED -> SUCCESS
         *  STARTED -> FAILED
         */
        promotionResultStatusTransitionPairs = new HashSet<>()
        promotionResultStatusTransitionPairs.add(Pair.of(Status.STARTED, Status.SUCCESS))
        promotionResultStatusTransitionPairs.add(Pair.of(Status.STARTED, Status.FAILED))
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
     * Patch the Deployment object with status update
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
         *  Check for valid status transitions. Throw exception if not found
         */
        if (!deploymentStatusTransitionPairs.containsAll(
         [Pair.of(deploy.status, deploymentUpdateMapper.status)])) {
            throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE)
        }

        /**
         * FIXME - inject triggerDeploymentStarted/Failed/Completed - issue #83
         */
    }

    /**
     * Add the promotion results on the Deployment object
     */
    @POST
    @Path('{id}/promotions')
    @UnitOfWork
    @Timed(name='post-requests')
    void addPromotionResult(@PathParam('id') LongParam deploymentId,
                            @Valid PromotionResultAddMapper promotionResultAddMapper) {
        Deployment deploy = this.dao.get(deploymentId.get())

        if (deploy == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND)
        }

        /**
         * Get Promotion Result model from deployment and throw error if not found
         */
        PromotionResult promotionResult = deploy.getPromotionResult(promotionResultAddMapper.promotionIdent)
        if (promotionResult == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND)
        }

        /**
         *  Check for valid status transitions. Throw exception if not found
         */
        if (!promotionResultStatusTransitionPairs.containsAll(
                [Pair.of(promotionResult.status, promotionResultAddMapper.status)])) {
            throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE)
        }

        /**
         * FIXME - inject triggerPromotionCompleted - issue #83
         */
    }
}
