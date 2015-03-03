package deploydb.mappers

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import deploydb.WebhookModelMapper
import deploydb.models.Artifact
import deploydb.models.DeploymentStatus


/**
 * Simple Jackson mapper class to deserialize deployment creation requests
 * properly
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
    DeploymentStatus status

    String toPayload() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this) ;
    }
}
