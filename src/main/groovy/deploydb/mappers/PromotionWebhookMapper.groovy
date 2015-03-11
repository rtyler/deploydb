package deploydb.mappers

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import deploydb.Status
import deploydb.models.PromotionResult


class PromotionWebhookMapper extends DeploymentWebhookMapper{

    /**
     *  Promotion result for webhook mapper
     */
    @JsonProperty
    PromotionResult promotionResult

    /**
     * Default constructor
     */
    PromotionWebhookMapper() { }
    /**
     *
     * @param deployment Deployment to map for the webhook
     * @param promotionResult Promotion result to map for the webhook
     */
    PromotionWebhookMapper(deploydb.models.Deployment deployment,
                                     PromotionResult promotionResult){
        this.id = deployment.id
        this.artifact = deployment.artifact
        this.service = deployment.serviceIdent
        this.environment = deployment.environmentIdent
        this.createdAt = deployment.createdAt
        this.status = Status.VERIFIED
        this.promotionResult = promotionResult
    }

    String toPayload() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this) ;
    }
}
