package webhookTestServer.models

/**
 * Class to represent the received object in webhook
 */
class RequestWebhookObject {

    /*
     * UriPath of the RequestWebhookObject
     */
    String configuredUriPath

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
    RequestWebhookObject(String configuredUriPath) {
        this.configuredUriPath = configuredUriPath
    }
}
