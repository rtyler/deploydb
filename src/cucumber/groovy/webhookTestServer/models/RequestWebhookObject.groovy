package webhookTestServer.models

/**
 * Class to represent the received object in webhook
 */
class RequestWebhookObject {

    /*
     * Url of the RequestWebhookObject
     */
    String configuredUrl

    /**
     *  Request message body of the object
     */
    String requestMessageBody

    /**
     *  Constructor with no arguments
     */
    RequestWebhookObject() { }

    void setRequestMessageBody(String requestMessageBody) {
        this.requestMessageBody = requestMessageBody
    }

    String getRequestMessageBody() {
        return this.requestMessageBody
    }

    /*
     * Constructor to initialize from application
     */
    RequestWebhookObject(String configuredUrl) {
        this.configuredUrl = configuredUrl
    }
}
