package webhookTestServer.resources

import ch.qos.logback.classic.pattern.ThreadConverter
import com.fasterxml.jackson.annotation.JsonProperty
import com.codahale.metrics.annotation.Timed
import com.google.common.base.Optional
import io.dropwizard.jersey.caching.CacheControl
import io.dropwizard.jersey.params.*
import io.dropwizard.hibernate.UnitOfWork
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import webhookTestServer.models.TestCallbackObject

import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.Context
import javax.ws.rs.core.Response
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicLong
/**
 *
 */
@Path("api/webhooks")
@Consumes(MediaType.APPLICATION_JSON)
public class TestCallBackobjectResource {

    private final Logger logger = LoggerFactory.getLogger(TestCallBackobjectResource.class)

    private final TestCallbackObject callbackObjectUnderTest

    @PUT
    @Path("{name}")
    @UnitOfWork
    @Timed(name='put-requests')
    public Response validateAndRespond(@PathParam('name') String name,
                                       @Valid TestCallbackObject callbackObject){

        /*
         * Initialize the name of the PUT object
         */
        callbackObject.name = name

        /*
         * If the name and payload is not valid request, then we throw BAD_REQUEST
         */
       if (! callbackObjectUnderTest.validNameAndPayload(callbackObject.name, callbackObject.payload)){
           throw new WebApplicationException(Response.Status.BAD_REQUEST)
       }

        /*
         * If there is a delay specified in the TestCallbackObject, then use it
         */
        if(callbackObjectUnderTest.delayBeforeResponseInSecs > 0){
            ThreadConverter.sleep(callbackObject.delayBeforeResponseInSecs*1000)
        }

        /*
         * If the payload is valid, then we respond with ACCEPTED
         */
        throw new WebApplicationException(Response.Status.ACCEPTED)
    }

    /*
     *  Empty constructor for object deserialization
     */
    TestCallBackobjectResource(){
    }



}
