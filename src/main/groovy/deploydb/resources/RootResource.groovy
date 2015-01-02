package deploydb.resources

import com.codahale.metrics.annotation.Timed
import com.google.common.base.Optional
import com.google.common.base.Charsets
import io.dropwizard.jersey.caching.CacheControl
import io.dropwizard.views.View

import org.slf4j.Logger
import org.slf4j.LoggerFactory


import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicLong

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

