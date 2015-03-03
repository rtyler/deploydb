package deploydb.mappers

import com.fasterxml.jackson.annotation.JsonProperty
import deploydb.Status

import javax.persistence.Entity


/**
 * Simple Jackson mapper class to deserialize
 * deployment update requests properly
 */
@Entity
class DeploymentUpdateMapper {
    @JsonProperty()
    Status status
}
