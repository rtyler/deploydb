package deploydb.models


import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.NotEmpty

import javax.validation.constraints.Size

/**
 *
 */
class WebhookPromotion {

    @NotEmpty
    @Size(max=2)
    @JsonProperty
    List<String> completed

    WebhookPromotion() {}

    WebhookPromotion(List<String> completed){
        this.completed = completed
    }

}
