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
import deploydb.provider.V1TypeProvider

@Path("/api/artifacts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(['application/json', 'application/vnd.deploydb.v1+json'])
public class ArtifactResource {
    private final ArtifactDAO dao
    private final Logger logger = LoggerFactory.getLogger(ArtifactResource.class)

    ArtifactResource(ArtifactDAO dao) {
        this.dao = dao
    }

    @POST
    @UnitOfWork
    @Timed(name='put-requests')
    Response createArtifact(@Valid Artifact artifact) {

        Artifact created = this.dao.persist(artifact)

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

