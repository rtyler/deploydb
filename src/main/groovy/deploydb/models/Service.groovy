package deploydb.models

import com.google.common.collect.Lists
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.Size
import org.hibernate.validator.constraints.NotEmpty

/**
 * Representation class for the concept of a Service
 */
class Service {

    /**
     * Service name
     */
    @NotEmpty
    @Size(max=8192)
    @JsonProperty
    String name

    /**
     * List of Artifact names (max 10).
     */
    @NotEmpty
    @Size(max=10)
    @JsonProperty
    List<String> artifacts

    /**
     * List of pipelines (max 10).
     */
    @NotEmpty
    @Size(max=10)
    @JsonProperty
    List<String> pipelines

    /**
     * List of promotions (max 10).
     */
    @JsonProperty
    @Size(max=10)
    List<String> promotions

    /**
     * Empty constructor used by Jackson for object deserialization
     */
    Service() { }

    /**
     *  Constructor to be used by DeployDB internally. It accepts all
     *  of the parameters
     */
    Service(String name,
            List<String> artifacts,
            List<String> pipelines,
            List<String> promotions) {
        this.name = name
        this.artifacts = artifacts
        this.pipelines = pipelines
        this.promotions = promotions
    }
}
