package webhookTestServer.resources

import com.codahale.metrics.annotation.Timed
import com.google.common.base.Charsets
import io.dropwizard.jersey.caching.CacheControl
import io.dropwizard.views.View

import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import java.util.concurrent.TimeUnit

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class RootResource {

    @GET
    @Timed(name = "get-requests")
    @CacheControl(maxAge = 1, maxAgeUnit = TimeUnit.DAYS)
    public View responder() {
        return new View('/views/index.mustache', Charsets.UTF_8) { }
    }
}