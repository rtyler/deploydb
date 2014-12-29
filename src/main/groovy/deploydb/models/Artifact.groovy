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
    private Long id

    @Column(name = "groupName", nullable = false)
    private String group

    @Column(name = "name", nullable = false)
    private String name


    /**
     * Empty constructor used by Jackson for object deserialization
     */
    Artifact() { }

    Artifact(String group, String name) {
        this.group = group
        this.name = name
    }


    /**
     * The internal identifier for the artifact
     */
    @JsonProperty
    Long getId() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true
        }

        if (!(o instanceof Artifact)) {
            return false
        }

        final Artifact that = (Artifact)o

        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.group, that.group) &&
                Objects.equals(this.name, that.name)
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.group, this.name)
    }
}
