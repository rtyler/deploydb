package deploydb.models

import com.google.common.collect.Lists
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.Size
import org.hibernate.validator.constraints.NotEmpty

/**
 * Representation class for the concept of a Environment
 */
class Environment {

    /**
     * Environment identifier
     *
     * Retrieved from the configuration base filename. Used as a key in
     * registry
     */
    @JsonProperty
    String ident

    /**
     * Environment description
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
    List<String> promotions

    /**
     * Empty constructor used by Jackson for object deserialization
     */
    Environment() { }

    /**
     *  Constructor to be used by DeployDB internally. It accepts all
     *  of the parameters
     */
    Environment(String ident,
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

        if (!(o instanceof Environment)) {
            return false
        }

        final Environment that = (Environment)o

        return Objects.equals(this.ident, that.ident) &&
                Objects.equals(this.description, that.description) &&
                Objects.equals(this.artifacts, that.artifacts) &&
                Objects.equals(this.pipelines, that.pipelines) &&
                Objects.equals(this.promotions, that.promotions)
    }

    @Override
    String toString() {
        String output
        output += "ident = ${ident}, description: ${description}, artifacts: ${artifacts}, "
        output += "pipelines = ${pipelines}, promotions = ${promotions}"
        return output
    }
}
