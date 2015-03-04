package deploydb.mappers

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import deploydb.WebhookModelMapper
import deploydb.models.Artifact
import deploydb.Status

/**
 * Simple Jackson mapper class to deserialize
 * deployment. Used during notifies to clients
 */
class DeploymentMapper implements WebhookModelMapper{

    @JsonProperty
    Long id

    @JsonProperty
    String createdAt

    @JsonProperty
    Artifact artifact

    @JsonProperty
    String environment

    @JsonProperty
    Status status

    String toPayload() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this) ;
    }
}
