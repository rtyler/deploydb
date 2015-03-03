package deploydb.mappers

import com.fasterxml.jackson.annotation.JsonProperty
import deploydb.Status
import javax.persistence.Entity
import org.hibernate.validator.constraints.NotEmpty


/**
 * Simple Jackson mapper class to deserialize
 * deployment update requests properly
 */
@Entity
class PromotionResultAddMapper {

    @NotEmpty
    @JsonProperty(value = "name")
    String promotionIdent

    @JsonProperty
    Status status = Status.NOT_STARTED

    @JsonProperty
    String infoUrl
}
