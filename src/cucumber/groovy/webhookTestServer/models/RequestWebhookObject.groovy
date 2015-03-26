package webhookTestServer.models

import com.google.common.collect.HashMultimap


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
     * ContentType header param
     */
    String contentTypeParam

    /**
     * Save all the header we received and we can compare them in steps
     */
    HashMultimap<String, String> headers = new HashMultimap<>()

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

    Boolean validateHeader(String headerName, String headerValue) {
        return headers.containsEntry(headerName, headerValue)
    }
}
