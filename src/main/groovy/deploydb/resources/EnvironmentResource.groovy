package deploydb.resources

import com.codahale.metrics.annotation.Timed
import io.dropwizard.jersey.caching.CacheControl
import io.dropwizard.jersey.params.*
import io.dropwizard.hibernate.UnitOfWork
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.Context
import javax.ws.rs.core.Response
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicLong

import deploydb.registry.ModelRegistry
import deploydb.models.Environment

/**
 * EnvironmentResource class registered with JettyClient for servicing REST request
 */
@Path("/api/environments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(['application/json', 'application/vnd.deploydb.v1+json'])
public class EnvironmentResource {
    private final ModelRegistry<Environment> environmentRegistry
    private final Logger logger = LoggerFactory.getLogger(EnvironmentResource.class)

    EnvironmentResource(ModelRegistry<Environment> environmentRegistry) {
        this.environmentRegistry = environmentRegistry
    }

    /**
     * Returns all Environment objects
     */
    @GET
    @UnitOfWork
    @Timed(name = "get-requests")
    List<Environment> getAll() {
        List<Environment> environmentTable = this.environmentRegistry.getAll()

        if (environmentTable.isEmpty()) {
            throw new WebApplicationException(Response.Status.NOT_FOUND)
        }
        return environmentTable
    }

    /**
     * Returns a Environment object
     */
    @GET
    @Path("{name}")
    @UnitOfWork
    @Timed(name = "get-requests")
    Environment byName(@PathParam("name") String environmentIdent) {
        Environment environment = this.environmentRegistry.get(environmentIdent)

        if (environment == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND)
        }
        return environment
    }
}

