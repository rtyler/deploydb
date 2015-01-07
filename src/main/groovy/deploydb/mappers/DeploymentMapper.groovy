package deploydb.mappers

import com.fasterxml.jackson.annotation.JsonProperty


/**
 * Simple Jackson mapper class to deserialize deployment creation requests
 * properly
 */
class DeploymentMapper {
    @JsonProperty
    Long artifactId

    @JsonProperty
    String environment
}
