package webhookTestServer.models

import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 */
class TestCallbackObject {

    String name

    @JsonProperty
    String payload

    String delayBeforeResponseInSecs

    /*
     * Empty constructor for object deserialization
     */
    TestCallbackObject(){}

    /*
     * Constructor to initialize from application
     */
    TestCallbackObject(String name,
            String payload,
            String delayBeforeResponseInSecs
    ){
        this.name = name
        this.payload = payload
        this.delayBeforeResponseInSecs = delayBeforeResponseInSecs
    }

    boolean validNameAndPayload(String name, String payload){
        return (this.name == name) && (this.payload == payload)
    }
}
