package deploydb

/**
 * Abstract class to map the webhook payload
 */
interface WebhookModelMapper {

    /*
     *  Get the webhook payload of the model
     */
     String toPayload()
}
