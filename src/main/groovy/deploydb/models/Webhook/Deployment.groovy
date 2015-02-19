package deploydb.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.NotEmpty

import javax.validation.constraints.Size

/**
 *
 */
class WebhookDeployment {

    @Size(max=2)
    @JsonProperty
    List<String> started

    @NotEmpty
    @Size(max=2)
    @JsonProperty
    List<String> created

    @NotEmpty
    @Size(max=2)
    @JsonProperty
    List<String> completed


    WebhookDeployment() {}

    WebhookDeployment(List<String> started,
                      List<String> created,
                      List<String> completed){
        this.started = started
        this.created = created
        this.completed = completed
    }
}
