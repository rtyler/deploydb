package deploydb.models.Pipeline

import com.fasterxml.jackson.annotation.JsonProperty
import com.google.common.collect.Maps

/**
 * Class for Environments associated with a pipeline
 */
class Environment {

    /**
     * List of promotions that should be run when you
     * transition from this environment
     */
    @JsonProperty
   List<String> promotions = []

    /**
     * Default constructor used by Jackson for deserialization
     */
    Environment(){}

    /**
     * Constructor with argument used by deployDb
     */
    Environment(List<String> promotions) {
        this.promotions = promotions
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

        if (!(o instanceof Environment)) {
            return false
        }

        final Environment that = (Environment)o

        return Objects.equals(this.promotions, that.promotions)
    }

    /**
     * Stringfy the environment
     */
    String toString(){
        String output
        output += "promotions:${promotions}"
        return output
    }
}
