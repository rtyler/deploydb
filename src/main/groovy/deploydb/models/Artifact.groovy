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
import org.joda.time.DateTime

/**
 * Representation class for the concept of an Artifact
 *
 */
@Entity
@Table(name='artifacts')
class Artifact {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id

    @Column(name="groupName", nullable=false)
    private String group

    @Column(name="name", nullable=false)
    private String name

    @Column(name='version', nullable=false)
    private String version

    @Column(name='sourceUrl')
    private String sourceUrl

    @Column(name='createdAt', nullable=false)
    private DateTime createdAt = DateTime.now()

    @Column(name='deletedAt')
    private DateTime deletedAt

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

    /**
     *  The version of the artifact (e.g. '1.0.1+0xbef')
     */
    @JsonProperty
    String getVersion() {
        return version
    }

    /**
     *  The URL where one might find this artifact (e.g.
     *  'http://example.com/dist/artifact.jar
     */
    @JsonProperty
    String getSourceUrl() {
        return sourceUrl
    }

    /**
     *
     */
    void setSourceUrl(String newSourceUrl) {
        this.sourceUrl = newSourceUrl
    }

    @JsonProperty
    String getCreatedAt() {
        /* toString on DateTime returns ISO-8601 by default */
        return createdAt.toString()
    }

    void setCreatedAt(String iso8601String) {
        this.createdAt = DateTime.parse(iso8601String)
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
