package deploydb.resources

import com.codahale.metrics.annotation.Timed
import com.google.common.base.Optional
import io.dropwizard.jersey.caching.CacheControl
import io.dropwizard.jersey.params.*
import io.dropwizard.hibernate.UnitOfWork
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.Context
import javax.ws.rs.core.Response
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicLong

import deploydb.dao.ArtifactDAO
import deploydb.models.Artifact

@Path("/api/v1/artifacts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ArtifactResource {
    private final ArtifactDAO dao
    private final Logger logger = LoggerFactory.getLogger(ArtifactResource.class)

    public ArtifactResource(ArtifactDAO dao) {
        this.dao = dao
    }

    @PUT
    @UnitOfWork
    @Timed(name='put-requests')
    public Response createArtifact(@Valid Artifact artifact) {

        Artifact created = this.dao.persist(artifact)

        return Response.status(201).entity(created).build()
    }

    @GET
    @Path("{id}")
    @UnitOfWork
    @Timed(name = "get-requests")
    public Artifact byIdentifier(@Context HttpHeaders headers,
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
    public List<Artifact> byName(@PathParam('group') String artifactGroup,
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
    public Artifact byNameLatest(@PathParam('group') String artifactGroup,
                         @PathParam("name") String artifactName){
        Artifact artifact = this.dao.findLatestByGroupAndName(artifactGroup,
                                                            artifactName)

        if (artifact == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND)
        }
        return artifact
    }

}

