package deploydb.models.Webhook

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.NotEmpty

import javax.validation.constraints.Size

/**
 * Class for Webhook's deployment configuration.
 */
class Deployment {

    /**
     * List of URI associated with deployment started state
     */
    @JsonProperty
    List<String> started

    /**
     * List of URI associated with deployment created state
     */
    @JsonProperty
    List<String> created

    /**
     * List of URI associated with deployment completed state
     */
    @JsonProperty
    List<String> completed

    /**
     * List of URI associated with deployment verified state
     */
    @JsonProperty
    List<String> verified
    
    /**
     * Empty constructor used by Jackson for object deserialization
     */
    Deployment() {}


    /**
     * Constructor with all the parameters specified used by deployDb
     */
    Deployment(List<String> started,
               List<String> created,
               List<String> completed,
               List<String> verified){
        this.started = started
        this.created = created
        this.completed = completed
        this.verified = verified
    }
}