package deploydb.resources

import com.codahale.metrics.annotation.Timed
import deploydb.models.Artifact
import deploydb.WorkFlow
import io.dropwizard.jersey.params.IntParam
import io.dropwizard.jersey.params.LongParam
import io.dropwizard.hibernate.UnitOfWork

import javax.servlet.http.HttpServletRequest
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
import org.slf4j.Logger
import org.slf4j.LoggerFactory


@Path("/api/artifacts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(['application/json', 'application/vnd.deploydb.v1+json'])
public class ArtifactResource {
    private final WorkFlow workFlow
    private final Logger logger = LoggerFactory.getLogger(ArtifactResource.class)

    ArtifactResource(WorkFlow workFlow) {
        this.workFlow = workFlow
    }

    @POST
    @UnitOfWork
    @Timed(name='put-requests')
    Response createArtifact(@Context HttpServletRequest request,
                            @Valid Artifact artifact) {

        this.workFlow.triggerArtifactCreated(artifact)

        String createdUri = request.getRequestURL() + "/${artifact.id}"
        return Response.created(createdUri.toURI()).entity(artifact).build()
    }

    @GET
    @Path("{id}")
    @UnitOfWork
    @Timed(name = "get-requests")
    Artifact byIdentifier(@Context HttpHeaders headers,
                               @PathParam("id") LongParam artifactId) {
        Artifact artifact = workFlow.artifactDAO.get(artifactId.get())

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
        List<Artifact> artifacts =
                this.workFlow.artifactDAO.findByGroupAndName(
                        artifactGroup, artifactName, artifactPageNumber.get(),
                        artifactPerPageSize.get())

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
        Artifact artifact =
                this.workFlow.artifactDAO.findLatestByGroupAndName(
                        artifactGroup, artifactName)

        if (artifact == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND)
        }
        return artifact
    }

}

