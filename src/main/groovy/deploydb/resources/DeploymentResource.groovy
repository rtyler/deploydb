package deploydb.resources

import com.codahale.metrics.annotation.Timed

import deploydb.Status
import deploydb.WorkFlow
import deploydb.mappers.PromotionResultAddMapper
import deploydb.models.Deployment
import deploydb.models.PromotionResult
import deploydb.mappers.DeploymentUpdateMapper

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
    private final WorkFlow workFlow

    DeploymentResource(WorkFlow workFlow) {
        this.workFlow = workFlow
    }

    /**
     * Returns all Deployment objects (paginate info is optional)
     */
    @GET
    @UnitOfWork
    @Timed(name = "get-requests")
    List<Deployment> getAll(
            @QueryParam("pageNumber") @DefaultValue("0") IntParam pageNumber,
            @QueryParam("perPageSize") @DefaultValue("20") deploydb.ModelPageSizeParam
                    perPageSize) {

        /**
         * Fetch deployment by page
         */
        List<Deployment> deployTable = this.workFlow.deploymentDAO.getByPage(
                pageNumber.get(), perPageSize.get())
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
        Deployment deploy = this.workFlow.deploymentDAO.get(deploymentId.get())

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
        Deployment deploy = this.workFlow.deploymentDAO.getLatest()

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
        Deployment deploy = this.workFlow.deploymentDAO.get(deploymentId.get())

        if (deploy == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND)
        }

        /**
         *  Check for valid status transitions. Throw exception if not found
         */
        if (!Deployment.deploymentStatusTransitionPairs.contains(
                Pair.of(deploy.status, deploymentUpdateMapper.status))) {
            throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE)
        }

        /* Update status */
        deploy.status = deploymentUpdateMapper.status

        /**
         * Inject the trigger in pseudo state machine
         */
        if (deploymentUpdateMapper.status == Status.STARTED) {
            this.workFlow.triggerDeploymentStarted(deploy)
        } else if (deploymentUpdateMapper.status == Status.COMPLETED) {
            this.workFlow.triggerDeploymentCompleted(deploy)
        } else if (deploymentUpdateMapper.status == Status.FAILED) {
            this.workFlow.triggerDeploymentFailed(deploy)
        }
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
        Deployment deploy = this.workFlow.deploymentDAO.get(deploymentId.get())

        if (deploy == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND)
        }

        /**
         * Get Promotion Result model from deployment and throw error if not found
         */
        PromotionResult promotionResult = deploy.getPromotionResult(
                promotionResultAddMapper.promotionIdent)
        if (promotionResult == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND)
        }

        /**
         *  Check for valid status transitions. Throw exception if not found
         */
        if (!PromotionResult.promotionResultStatusTransitionPairs.contains(
                Pair.of(promotionResult.status, promotionResultAddMapper.status))) {
            throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE)
        }

        /* Add results */
        promotionResult.status = promotionResultAddMapper.status
        promotionResult.infoUrl = promotionResultAddMapper.infoUrl

        /**
         * Inject the trigger in pseudo state machine
         */
        if (promotionResultAddMapper.status == Status.SUCCESS) {
            this.workFlow.triggerPromotionSuccess(deploy, promotionResult)
        } else if (promotionResultAddMapper.status == Status.FAILED) {
            this.workFlow.triggerPromotionFailed(deploy, promotionResult)
        }
    }
}
