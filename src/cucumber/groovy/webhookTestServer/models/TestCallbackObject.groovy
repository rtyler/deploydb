package webhookTestServer.models

import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 */
class TestCallbackObject {

    /*
     * Url of the TestCallbackObject
     */
    String url

    /*
     * Expected method of webhook object - POST, UPDATE
     */
    String method

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
    String delayBeforeResponseInSecs

    /*
     * Empty constructor for object deserialization
     */
    TestCallbackObject(){}

    /*
     * Constructor to initialize from application
     */
    TestCallbackObject(String url,
            String method,
            String responseCode,
            String responseData,
            String delayBeforeResponseInSecs
    ){
        this.url = url
        this.method = method
        this.responseCode = responseCode
        this.responseData = responseData
        this.delayBeforeResponseInSecs = delayBeforeResponseInSecs
    }


    boolean validMethodAndUri(String method, String requestUri){
        return (this.method == method) && (this.requestUri == requestUri)
    }
}
