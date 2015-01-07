package deploydb.models

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.NamedQueries
import javax.persistence.NamedQuery
import javax.persistence.Table
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length

/**
 * Representation class for the concept of an Artifact
 */
@Entity
@Table(name='artifacts')
class Artifact extends AbstractModel {

    @Column(name="groupName", nullable=false)
    @JsonProperty
    String group

    @Column(name="name", nullable=false)
    @JsonProperty
    String name

    @Column(name='version', nullable=false)
    @JsonProperty
    String version

    @Column(name='sourceUrl')
    @JsonProperty
    String sourceUrl

    /**
     * Empty constructor used by Jackson for object deserialization
     */
    Artifact() { }

    /**
     *  Default constructor to be used by DeployDB internally. It accepts all
     *  of the required parameters for the database
     */
    Artifact(String group,
             String name,
             String version) {
        this.group = group
        this.name = name
        this.version = version
    }

    @Override
    public boolean equals(Object o) {
        /* First object identity */
        if (this.is(o)) {
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
