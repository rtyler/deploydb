package deploydb.models

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

/**
 * Representation class for the concept of an Artifact
 *
 */
class Artifact {
    private long id
    private String group
    private String name


    /**
     * Empty constructor used by Jackson for object deserialization
     */
    Artifact() {
    }


    /**
     * The internal identifier for the artifact
     */
    @JsonProperty
    long getId() {
        return id
    }

    /**
     * The group (e.g. 'com.github' for the artifact)
     */
    @JsonProperty
    String getGroup() {
        return group
    }

    /**
     * The name of the artifact (e.g. 'deploydb.jar')
     */
    @JsonProperty
    String getName() {
        return name
    }
}
