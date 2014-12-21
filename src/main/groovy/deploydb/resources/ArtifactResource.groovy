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
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicLong

@Path("/api/v1/artifacts")
@Produces(MediaType.APPLICATION_JSON)
public class ArtifactResource {
    public ArtifactResource() {
    }

    @GET
    @Path("{id}")
    @Timed(name = "get-requests")
    public String byIdentifier(@PathParam("id") LongParam artifactId) {
        return ""
    }

    @GET
    @Path("named/{name}")
    @Timed(name = "get-requests")
    public String byName(@PathParam("name") String artifactId) {
        return ""
    }
}

