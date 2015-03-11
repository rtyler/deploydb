package deploydb.mappers

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import deploydb.WebhookModelMapper
import deploydb.models.Artifact
import deploydb.Status
import javax.persistence.Entity

/**
 * Simple Jackson mapper class to deserialize
 * deployment. Used during webhook notifies to clients
 */
class DeploymentWebhookMapper implements WebhookModelMapper{

    @JsonProperty
    protected Long id

    @JsonProperty
    protected Artifact artifact

    @JsonProperty
    protected String service

    @JsonProperty
    protected String environment

    @JsonProperty
    protected String createdAt

    @JsonProperty
    protected Status status

    DeploymentWebhookMapper() { }

    /**
     *  Constructor that takes the deployment argument
     */
    DeploymentWebhookMapper(deploydb.models.Deployment deployment){
        this.id = deployment.id
        this.artifact = deployment.artifact
        this.service = deployment.serviceIdent
        this.environment = deployment.environmentIdent
        this.createdAt = deployment.createdAt
        this.status = deployment.status
    }

    String toPayload() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this) ;
    }
}



/**
 *  Simple Jackson mapper class to deserialzie
 *  deployment update requests properly
 */
@Entity
class DeploymentUpdateMapper {
    @JsonProperty
    Status status
}