package deploydb.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.NotEmpty

import javax.validation.constraints.Size

/**
 * Class to store webhook configuration
 */
class Webhook {

    /**
     * List of Artifact names (max 10).
     */
    @NotEmpty
    @Size(max=10)
    @JsonProperty
    List<WebhookDeployment> webhookDeploymentList

    /**
     * List of promotions (max 10).
     */
    @JsonProperty
    @Size(max=10)
    List<WebhookPromotion> webhookPromotionList

    /**
     * Empty constructor used by Jackson for object deserialization
     */
    Webhook() { }

    /**
     *  Constructor to be used by DeployDB internally. It accepts all
     *  of the parameters
     */
    Webhook(List<WebhookDeployment> webhookDeploymentList,
            List<WebhookPromotion> webhookPromotionList) {
        this.webhookDeploymentList = webhookDeploymentList
        this.webhookPromotionList = webhookPromotionList
    }
}
