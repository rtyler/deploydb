package deploydb.models

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

/**
 * Representation class for the concept of an Artifact
 *
 */
@Entity
@Table(name='artifacts')
class Artifact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id

    @Column(name = "groupName", nullable = false)
    private String group

    @Column(name = "name", nullable = false)
    private String name


    /**
     * Empty constructor used by Jackson for object deserialization
     */
    Artifact() { }

    Artifact(long id, String group, String name) {
        this.id = id
        this.group = group
        this.name = name
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
