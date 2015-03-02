package deploydb.resources

import com.codahale.metrics.annotation.Timed
import io.dropwizard.jersey.params.LongParam
import io.dropwizard.hibernate.UnitOfWork

import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.PathParam
import javax.ws.rs.WebApplicationException
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

import deploydb.dao.ArtifactDAO
import deploydb.dao.DeploymentDAO
import deploydb.dao.FlowDAO
import deploydb.models.Flow

@Path("/api/flows")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FlowResource {
    private final DeploymentDAO dao
    private final ArtifactDAO adao
    private final FlowDAO fdao

    FlowResource(FlowDAO fdao, DeploymentDAO dao, ArtifactDAO adao) {
        this.fdao = fdao
        this.dao = dao
        this.adao = adao
    }

    @GET
    @Path("{id}")
    @UnitOfWork
    @Timed(name = "get-requests")
    Flow byIdentifier(@PathParam("id") LongParam flowId) {
        Flow flow = this.fdao.get(flowId.get())

        if (flow == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND)
        }

        return flow
    }

}
