package deploydb.models.Webhook

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Class to store webhook configuration
 */
class Webhook {

    /**
     * List of deployments that are part of this webhook configuration.
     */
    @JsonProperty
    Deployment deployment

    /**
     * List of promotions that are part of this webhook configuration.
     */
    @JsonProperty
    Promotion promotion

    /**
     * Empty constructor used by Jackson for object deserialization
     */
    Webhook() { }

    /**
     *  Constructor to be used by DeployDB internally. It accepts all
     *  of the parameters
     */
    Webhook(Deployment deployment,
            Promotion promotion) {
        this.deployment = deployment
        this.promotion = promotion
    }

    @Override
    boolean equals(Object o) {

        /* First object identity */
        if (this.is(o)) {
            return true
        }

        if (!(o instanceof Webhook)) {
            return false
        }

        final Webhook that = (Webhook)o

        return Objects.equals(this.deployment, that.deployment) &&
                Objects.equals(this.promotion, that.promotion)
    }

    @Override
    int hashCode() {
        return Objects.hash(this.deployment, this.promotion)
    }

    @Override
    String toString() {
        return "deployment: ${deployment}, promotion: ${promotion}"
    }
}
