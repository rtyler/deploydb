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

    public ArtifactResource(ArtifactDAO dao) {
        this.dao = dao
    }

    @GET
    @Path("{id}")
    @UnitOfWork
    @Timed(name = "get-requests")
    public Artifact byIdentifier(@Context HttpHeaders headers,
                               @PathParam("id") LongParam artifactId) {
        Artifact artifact = this.dao.findById(artifactId.get())

        if (artifact == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND)
        }
        return artifact
    }

    @GET
    @Path("by-module/{group}:{name}")
    @UnitOfWork
    @Timed(name = "get-requests")
    public Artifact byName(@PathParam('group') String artifactGroup,
                         @PathParam("name") String artifactName) {
        Artifact artifact = this.dao.findByGroupAndName(artifactGroup,
                                                        artifactName)

        if (artifact == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND)
        }
        return artifact
    }
}

