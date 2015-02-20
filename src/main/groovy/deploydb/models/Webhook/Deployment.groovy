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
    private List<String> started

    /**
     * List of URI associated with deployment created state
     */
    @JsonProperty
    private List<String> created

    /**
     * List of URI associated with deployment completed state
     */
    @JsonProperty
    private List<String> completed

    /**
     * List of URI associated with deployment verified state
     */
    @JsonProperty
    private List<String> verified

    /*
     * Access function for created
     */
    public List<String> getCreated(){
        return this.created
    }

    /*
     * Access function for started
     */
    public List<String> getStarted(){
        return this.started
    }

    /*
     * Access function for completed
     */
    public List<String> getCompleted(){
        return this.completed
    }

    /*
     * Access function for verified
     */
    public List<String> getVerified(){
        return this.verified
    }

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