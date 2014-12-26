package deploydb.resources

import com.codahale.metrics.annotation.Timed
import com.google.common.base.Optional
import io.dropwizard.jersey.caching.CacheControl
import io.dropwizard.jersey.params.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.Context
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicLong

import deploydb.dao.ArtifactDAO
import deploydb.models.Artifact

@Path("/api/v1/artifacts")
//@Produces(MediaType.APPLICATION_JSON)
@Produces('application/json')
@Consumes(MediaType.APPLICATION_JSON)
public class ArtifactResource {
    private final ArtifactDAO dao

    public ArtifactResource(ArtifactDAO dao) {
        this.dao = dao
    }

    @GET
    @Path("{id}")
    @Timed(name = "get-requests")
    public Artifact byIdentifier(@Context HttpHeaders headers,
                               @PathParam("id") LongParam artifactId) {
        return this.dao.findById(artifactId.get())
    }

    @GET
    @Path("named/{name}")
    @Timed(name = "get-requests")
    public String byName(@PathParam("name") String artifactId) {
        return ""
    }
}

