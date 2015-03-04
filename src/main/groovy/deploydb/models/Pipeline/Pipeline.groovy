package deploydb.models.pipeline

import com.fasterxml.jackson.annotation.JsonProperty


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
     * Value represents the list of deploydb.models.pipeline.Environment that
     * are part of this pipeline
     * Key to the map deploydb.models.Environment ident
     */
    @JsonProperty
    Map<String, Environment> environments =
            new LinkedHashMap<String, Environment>()

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
     * Function to calculate hash for pipeline
     *
     * @return hash value
     */
    @Override
    int hashCode() {
        return Objects.hash(this.ident, this.description, this.environments)
    }
    /**
     * Stringfy the pipeline
     */
    String toString(){
        return "ident = ${ident}, description: ${description}, environments:${environments}"
    }
}
