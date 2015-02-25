package deploydb.models.Pipeline

import com.fasterxml.jackson.annotation.JsonProperty

import javax.validation.OverridesAttribute
import com.google.common.collect.Maps

/**
 * Class for deployment pipeline. It list all the environments
 * that are part of the pipeline
 */
class Pipeline {

    /**
     *
     */
    @JsonProperty
    String ident

    /**
     * Description of the pipeline
     */
    @JsonProperty
    String description

    /**
     * list of environments that are part of this pipeline
     */
    @JsonProperty
    Map<String, Environment> environments

    /**
     * Default constructor used by Jackson for deserialization
     */
    Pipeline() { }

    /**
     * Constructor used by deployDb
     */
    Pipeline(String ident, String description, Map<String, Environment> environments) {
        this.ident = ident
        this.description = description
        this.environments = environments
    }

    /**
     * Equality operator
     */
    @Override
    boolean equals(Object o) {
        /* First object identity */
        if (this.is(o)) {
            return true
        }

        if (!(o instanceof Pipeline)) {
            return false
        }

        final Pipeline that = (Pipeline)o

        return Objects.equals(this.ident, that.ident) &&
                Objects.equals(this.description, that.description) &&
                Objects.equals(this.environments, that.environments)

    }

    /**
     * Stringfy the pipeline
     */
    String toString(){
        String output
        output += "ident = ${ident}, description: ${description}, environments:${environments}"
        return output
    }
}
