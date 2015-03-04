package deploydb.resources

import com.codahale.metrics.annotation.Timed
import io.dropwizard.jersey.params.IntParam
import io.dropwizard.jersey.params.LongParam
import io.dropwizard.hibernate.UnitOfWork
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.validation.Valid
import javax.ws.rs.Consumes
import javax.ws.rs.DefaultValue
import javax.ws.rs.GET
import javax.ws.rs.QueryParam
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.POST
import javax.ws.rs.Produces
import javax.ws.rs.WebApplicationException
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.Context
import javax.ws.rs.core.Response

import deploydb.WebhookManager
import deploydb.Status
import deploydb.dao.ArtifactDAO
import deploydb.models.Artifact
import deploydb.mappers.DeploymentWebhookMapper

@Path("/api/artifacts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(['application/json', 'application/vnd.deploydb.v1+json'])
public class ArtifactResource {
    private final ArtifactDAO dao
    private final WebhookManager webhookManager
    private final Logger logger = LoggerFactory.getLogger(ArtifactResource.class)

    ArtifactResource(ArtifactDAO dao, WebhookManager webhookManagerArg) {
        this.dao = dao
        this.webhookManager = webhookManagerArg
    }

    @POST
    @UnitOfWork
    @Timed(name='put-requests')
    Response createArtifact(@Valid Artifact artifact) {

        Artifact created = this.dao.persist(artifact)

        /*
         * create the deployment, get all the configured models
         */
        DeploymentWebhookMapper deploymentWebhookMapper = new DeploymentWebhookMapper()
        deploymentWebhookMapper.id = created.id
        deploymentWebhookMapper.createdAt = created.createdAt
        deploymentWebhookMapper.artifact = created
        deploymentWebhookMapper.status = Status.STARTED
        deploymentWebhookMapper.environment = "dev-integ"

        if (webhookManager.createDeploymentWebhook(deploymentWebhookMapper, "CREATED") == false) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST)
        }
        return Response.status(201).entity(created).build()
    }

    @GET
    @Path("{id}")
    @UnitOfWork
    @Timed(name = "get-requests")
    Artifact byIdentifier(@Context HttpHeaders headers,
                               @PathParam("id") LongParam artifactId) {
        Artifact artifact = this.dao.get(artifactId.get())

        if (artifact == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND)
        }
        return artifact
    }

    @GET
    @Path("by-module/{group}:{name}")
    @UnitOfWork
    @Timed(name = "get-requests")
    List<Artifact> byName(@PathParam('group') String artifactGroup,
                         @PathParam("name") String artifactName,
                         @QueryParam("pageNumber") @DefaultValue("0") IntParam artifactPageNumber,
                         @QueryParam("perPageSize") @DefaultValue("5") IntParam artifactPerPageSize){
        List<Artifact> artifacts = this.dao.findByGroupAndName(artifactGroup,
                                                        artifactName, artifactPageNumber.get(), artifactPerPageSize.get())

        if (artifacts.isEmpty()) {
            throw new WebApplicationException(Response.Status.NOT_FOUND)
        }
        return artifacts
    }

    @GET
    @Path("by-module/{group}:{name}/latest")
    @UnitOfWork
    @Timed(name = "get-requests")
    Artifact byNameLatest(@PathParam('group') String artifactGroup,
                         @PathParam("name") String artifactName){
        Artifact artifact = this.dao.findLatestByGroupAndName(artifactGroup,
                                                            artifactName)

        if (artifact == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND)
        }
        return artifact
    }

}

