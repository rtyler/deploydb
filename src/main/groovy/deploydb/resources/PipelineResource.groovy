package deploydb.resources

import com.codahale.metrics.annotation.Timed
import com.sun.research.ws.wadl.Response
import deploydb.models.pipeline.Pipeline
import deploydb.registry.ModelRegistry
import io.dropwizard.hibernate.UnitOfWork
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.Response
import javax.ws.rs.WebApplicationException
import javax.ws.rs.core.MediaType

/**
 * Resource to handle the REST api for pipeline
 */
@Path("/api/pipelines")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(['application/json', 'application/vnd.deploydb.v1+json'])
public class PipelineResource {
    private final ModelRegistry<Pipeline> pipelineRegistry
    private final Logger logger = LoggerFactory.getLogger(PipelineResource.class)

    PipelineResource(ModelRegistry<Pipeline> pipelineRegistry) {
        this.pipelineRegistry = pipelineRegistry
    }

    /**
     * Return all the configured pipelines
     */
    @GET
    @UnitOfWork
    @Timed(name = "get-requests")
    List<Pipeline> getAll() {

        List<Pipeline> pipelineList = this.pipelineRegistry.getAll()

        if (pipelineList.isEmpty()) {
            throw new WebApplicationException(Response.Status.NOT_FOUND)
        }
        return pipelineList
    }

    /**
     * Return a configured Pipeline
     */
    @GET
    @Path("{name}")
    @UnitOfWork
    @Timed(name = "get-requests")
    Pipeline byName(@PathParam("name") String pipelineIdent) {

        Pipeline pipeline = this.pipelineRegistry.get(pipelineIdent)

        if (pipeline == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND)
        }
        return pipeline
    }
}