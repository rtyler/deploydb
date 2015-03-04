package deploydb.models.pipeline

import com.fasterxml.jackson.annotation.JsonProperty
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Class for Environments associated with a pipeline
 */
class Environment {

    private final Logger logger = LoggerFactory.getLogger(Environment.class)
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
     *  This constructor is for JSON to call us to create an empty
     *  promotions for Environment object.
     *
     * @param nullJsonString
     */
    Environment(String nullJsonString) {
        if (nullJsonString.size() == 0) {
            this.promotions = []
        } else {
            logger.error("Error creating Environment form JSON %s",nullJsonString)
        }
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
     * Compute the hash value for environment
     *
     * @return computed hash value
     */
    @Override
    int hashCode() {
        return Objects.hash(this.promotions)
    }

    /**
     * Stringfy the environment
     */
    String toString() {
        return "promotions: ${promotions}"
    }
}
