package webhookTestServer.resources

import ch.qos.logback.classic.pattern.ThreadConverter
import com.codahale.metrics.annotation.Metered
import com.fasterxml.jackson.annotation.JsonProperty
import com.codahale.metrics.annotation.Timed
import com.google.common.base.Optional
import io.dropwizard.jersey.caching.CacheControl
import io.dropwizard.jersey.params.*
import io.dropwizard.hibernate.UnitOfWork
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import webhookTestServer.models.TestCallbackObject

import javax.servlet.http.HttpServletRequest
import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.Context
import javax.ws.rs.core.Response
import javax.ws.rs.core.UriInfo
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicLong

/**
 *
 */
@Consumes(MediaType.APPLICATION_JSON)
public class TestCallBackobjectResource {

    private final Logger logger = LoggerFactory.getLogger(TestCallBackobjectResource.class)
    @Context private HttpServletRequest servletRequest

    private final TestCallbackObject callbackObjectUnderTest

    @Metered
    public Response validateAndRespond(){

        String httpMethod = this.servletRequest.getMethod()
        String requestUri = this.servletRequest.requestURI()

        logger.debug(" method %s requestUri %s", httpMethod, requestUri)

        /*
         * If the name and payload is not valid request, then we throw BAD_REQUEST
         */
        if (! callbackObjectUnderTest.validMethodAndUri(httpMethod, requestUri)){
            throw new WebApplicationException(Response.Status.BAD_REQUEST)
        }



        /*
         * If the payload is valid, then we respond with configured data and response code
         */
        return Response.status(callbackObjectUnderTest.responseCode.toInteger())
                       .entity(callbackObjectUnderTest.responseData)
                       .sleep(callbackObjectUnderTest.delayBeforeResponseInSecs/1000)
    }

    /*
     *  Empty constructor for object deserialization
     */
    TestCallBackobjectResource(){
    }



}
