package webhookTestServer.resources

import io.dropwizard.hibernate.UnitOfWork
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import webhookTestServer.models.RequestWebhookObject
import webhookTestServer.models.ResponseWebhookObject

import javax.servlet.http.HttpServletRequest
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.WebApplicationException
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Context
import javax.ws.rs.core.Response

/**
 *
 */
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TestCallBackobjectResource {

    private final Logger logger = LoggerFactory.getLogger(TestCallBackobjectResource.class)
    private final RequestWebhookObject requestWebhookObject
    private final ResponseWebhookObject responseWebhookObject
    private boolean validObject = false

    TestCallBackobjectResource(RequestWebhookObject RequestWebhookObject,
                               ResponseWebhookObject responseWebhookObject) {
        this.requestWebhookObject = RequestWebhookObject
        this.responseWebhookObject = responseWebhookObject
    }

    @POST
    @Path("{subResources:.*}")
    @UnitOfWork
    Response createWebhookResource(@Context HttpServletRequest request, String messageBody) {

        String requestUri = request.getRequestURI()

        /*
         * If the received request URI is not same as configured, then we throw BAD_REQUEST
         */
        if (requestWebhookObject.configuredUriPath != requestUri){
            throw new WebApplicationException(Response.Status.BAD_REQUEST)
        }

        /*
         * save the received message body
         */
        requestWebhookObject.setRequestMessageBody(messageBody)

        /*
         * If the payload is valid, then we respond with configured data and response code
         */
        this.validObject = true
        return Response.status(responseWebhookObject.responseCode.toInteger())
                       .entity(responseWebhookObject.responseData)
                       .sleep(responseWebhookObject.delayBeforeResponseInSecs)
    }

}
