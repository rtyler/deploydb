package webhookTestServer.models

/**
 * Class to represent the received object in webhook
 */
class ReceivedWebhookObject {

    /*
     * Url of the ReceivedWebhookObject
     */
    String configuredUrl

    /**
     *  Request message body of the object
     */
    String requestMessageBody

    /**
     *  Constructor with no arguments
     */
    ReceivedWebhookObject() { }

    void setRequestMessageBody(String requestMessageBody) {
        this.requestMessageBody = requestMessageBody
    }

    String getRequestMessageBody() {
        return this.requestMessageBody
    }

    /*
     * Constructor to initialize from application
     */
    ReceivedWebhookObject(String configuredUrl) {
        this.configuredUrl = configuredUrl
    }
}
