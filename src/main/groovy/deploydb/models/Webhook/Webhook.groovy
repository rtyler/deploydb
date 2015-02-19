package deploydb.models.Webhook

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.NotEmpty

import javax.validation.constraints.Size

/**
 * Class to store webhook configuration
 */
class Webhook {

    /**
     * List of deployments that are part of this webhook configuration.
     */
    @JsonProperty
    List<Deployment> webhookDeploymentList

    /**
     * List of promotions that are part of this webhook configuration.
     */
    @JsonProperty
    List<Promotion> webhookPromotionList

    /**
     * Empty constructor used by Jackson for object deserialization
     */
    Webhook() { }

    /**
     *  Constructor to be used by DeployDB internally. It accepts all
     *  of the parameters
     */
    Webhook(List<Deployment> webhookDeploymentList,
            List<Promotion> webhookPromotionList) {
        this.webhookDeploymentList = webhookDeploymentList
        this.webhookPromotionList = webhookPromotionList
    }
}
