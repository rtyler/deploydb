package deploydb.mappers

import com.fasterxml.jackson.annotation.JsonProperty
import deploydb.Status


/**
 * Simple Jackson mapper class to deserialize deployment creation requests
 * properly
 */
class DeploymentUpdateMapper {
    @JsonProperty()
    Status status
}
