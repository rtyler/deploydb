package webhookTestServer.models

import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 */
class ResponseWebhookObject {


    /*
     * Response code this object should return for the webhook invocation
     */
    String responseCode

    /*
     * Response data this object should return for the webook invocation
     */
    @JsonProperty
    String responseData

    /*
     * Wait for before sending response
     */
    long delayBeforeResponseInSecs

    /**
     * Constructor with no arguments
     *
     */
    ResponseWebhookObject() { }

    /**
    * Constructor to initialize from application
    */
    ResponseWebhookObject(String responseCode, String responseData, long delayBeforeResponseInSecs) {
        this.responseCode = responseCode
        this.responseData = responseData
        this.delayBeforeResponseInSecs = delayBeforeResponseInSecs
    }

}
