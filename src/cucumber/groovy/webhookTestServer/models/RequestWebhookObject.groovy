package webhookTestServer.models


/**
 * Class to represent the received object in webhook
 */
class RequestWebhookObject {

    /*
     * UriPath of the RequestWebhookObject
     */
    List<String> configuredUriPaths = []

    /**
     *  Request message body of the object
     */
    List<String> requestMessageBodies = []

    /**
     *  Constructor with no arguments
     */
    RequestWebhookObject() { }

    void addConfiguredUriPaths(List<String> paths) {
        this.configuredUriPaths += paths
    }

    void setRequestMessageBody(String requestMessageBody) {
        this.requestMessageBodies.add(requestMessageBody)
    }

    String getRequestMessageBody() {
        return this.requestMessageBodies[0]
    }
}
