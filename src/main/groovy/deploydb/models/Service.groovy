package deploydb.models

import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.validation.OneOf

import javax.validation.constraints.Size
import org.hibernate.validator.constraints.NotEmpty

/**
 * Representation class for the concept of a Service
 */
class Service {

    /**
     * Service identifier
     *
     * Retrieved from the configuration base filename. Used as a key in
     * registry
     */
    @JsonProperty
    String ident

    /**
     * Service description
     */
    @NotEmpty
    @Size(max=8192)
    @JsonProperty
    String description

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
    List<String> promotions = []

    /**
     * Failure Strategy.
     *
     * Defines actions to take on deployment failure for this Service:
     * Stop (default), rollback (future), full_rollback (future)
     */
    @OneOf(value = ["Stop"], ignoreCase = true)
    @JsonProperty
    String failure_strategy = "Stop"

    /**
     * Empty constructor used by Jackson for object deserialization
     */
    Service() { }

    /**
     *  Constructor to be used by DeployDB internally. It accepts all
     *  of the parameters
     */
    Service(String ident,
            String description,
            List<String> artifacts,
            List<String> pipelines,
            List<String> promotions) {
        this.ident = ident
        this.description = description
        this.artifacts = artifacts
        this.pipelines = pipelines
        this.promotions = promotions
    }

    @Override
    boolean equals(Object o) {

        /* First object identity */
        if (this.is(o)) {
            return true
        }

        if (!(o instanceof Service)) {
            return false
        }

        final Service that = (Service)o

        return Objects.equals(this.ident, that.ident) &&
                Objects.equals(this.description, that.description) &&
                Objects.equals(this.artifacts, that.artifacts) &&
                Objects.equals(this.pipelines, that.pipelines) &&
                Objects.equals(this.promotions, that.promotions)
    }

    @Override
    int hashCode() {
        return Objects.hash(this.ident, this.description)
    }

    @Override
    String toString() {
        return "ident = ${ident}, description: ${description}, artifacts: ${artifacts}, pipelines = ${pipelines}, promotions = ${promotions}"
    }
}
