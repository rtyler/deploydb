package deploydb.resources

import com.codahale.metrics.annotation.Timed
import com.sun.research.ws.wadl.Response
import deploydb.models.Promotion
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
 * Resource to handle the REST api for promotion
 */
@Path("/api/promotions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(['application/json', 'application/vnd.deploydb.v1+json'])
public class PromotionResource {
    private final ModelRegistry<Promotion> promotionRegistry
    private final Logger logger = LoggerFactory.getLogger(PromotionResource.class)

    PromotionResource(ModelRegistry<Promotion> promotionRegistry){
        this.promotionRegistry = promotionRegistry
    }

    /**
     * Return all the configured promotions
     */
    @GET
    @UnitOfWork
    @Timed(name = "get-requests")
    List<Promotion> getAll(){
        List<Promotion> promotionList = this.promotionRegistry.getAll()

        if(promotionList.isEmpty()){
            throw new WebApplicationException(Response.Status.NOT_FOUND)
        }
        return promotionList
    }

    /**
     * Return a configured Promotion
     */
    @GET
    @Path("{name}")
    @UnitOfWork
    @Timed(name = "get-requests")
    Promotion byName(@PathParam("name") String promotionIdent){
        Promotion promotion = this.promotionRegistry.get(promotionIdent)

        if(promotion == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND)
        }
        return promotion
    }
}
