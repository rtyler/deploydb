package deploydb.models

import com.fasterxml.jackson.annotation.JsonProperty

/**
 *  Class for promotion of the deployment from one environment to
 *  next in a pipeline
 */
class Promotion {

    /**
     * Promotion identifier
     *
     * Retrieved from the configuration base filename. Used as a key in
     * registry
     */
    @JsonProperty
    String ident

    /**
     * Type of the promotion
     *
     */
    @JsonProperty
    String type

    /**
     * Description of this promotion
     */
    @JsonProperty
    String description

    /**
     * Empty constructor used by Jackson during deserialaization
     */
    Promotion(){}

    /**
     * Constructor with Arguments
     */
    Promotion(String ident, String type, String description){
        this.ident = ident
        this.type = type
        this.description = description
    }

    /**
     *  Compare function
     */
    @Override
    boolean equals(Object o) {

        /* First object identity */
        if (this.is(o)) {
            return true
        }

        if (!(o instanceof Promotion)) {
            return false
        }

        final Promotion that = (Promotion)o

        return Objects.equals(this.ident, that.ident) &&
                Objects.equals(this.type, that.type) &&
                Objects.equals(this.description, that.description)
    }


    @Override
    int hashCode() {
        return Objects.hash(this.ident, this.type, this.Description)
    }


    /**
     * Stringy the promotion
     */
    @Override
    String toString() {
        return "ident = ${ident}, type = ${type}, description = ${description}"
    }
}

