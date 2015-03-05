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
    Long id

    @JsonProperty
    Artifact artifact

    @JsonProperty
    String service

    @JsonProperty
    String environment

    @JsonProperty
    String createdAt

    @JsonProperty
    Status status

    /**
     *  Constructor with all the arguments
     * @param id Identity of the deployment
     * @param artifact Deployed artifact
     * @param service Service that this artifact is part of
     * @param environment Environment that this deployment is in now
     * @param createdAt Deployment create time
     * @param status Status of the Deployment
     */
    DeploymentWebhookMapper(Long id, Artifact artifact, String service,
                            String environment, String createdAt, Status status){
        this.id = id
        this.artifact = artifact
        this.service = service
        this.environment = environment
        this.createdAt = createdAt
        this.status = status
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