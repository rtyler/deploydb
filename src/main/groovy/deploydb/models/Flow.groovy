package deploydb.models


import org.hibernate.annotations.CascadeType

import javax.persistence.Column

import com.fasterxml.jackson.annotation.JsonProperty

import javax.persistence.CascadeType;
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table

/**
 * Class to represent the flow as artifact moves from one
 * deployment to next one.
 */
@Entity
@Table(name='flows')
class Flow extends AbstractModel {

    @OneToOne(optional=false)
    @JoinColumn(name='artifactId', unique=false, nullable=false, updatable=false)
    @JsonProperty
    Artifact artifact

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, mappedBy = "flow")
    @JsonProperty
    @javax.persistence.OrderBy("id")
    Set<Deployment> deployments = new HashSet<Deployment>()

    @Column(name="service")
    @JsonProperty
    String service

    void addDeployment(Deployment deployment) {
        deployments.add(deployment)
    }

    /**
     * Empty constructor used by Jackson for object deserialization
     */
    Flow() { }

    /**
     * Default constructor to create a valid Flow object to save in
     * the database
     */
    Flow(Artifact deployedArtifact, Set<Deployment> deployments,
         String service) {
        this.artifact = deployedArtifact
        this.deployments = deployments
        this.service = service
    }

    /**
     * Equality operator
     *
     * @param o Object to test equality
     * @return True if the objects are equal otherwise false
     */
    @Override
    public boolean equals(Object o) {
        /* First object identity */
        if (this.is(o)) {
            return true
        }

        if (!(o instanceof Flow)) {
            return false
        }

        final Flow that = (Flow)o

        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.artifact, that.artifact) &&
                Objects.equals(this.deployments, that.deployments) &&
                Objects.equals(this.service, that.service)
    }

    /**
     * Compute the hash of the flow using memeber variables
     *
     * @return Computed hash code
     */
    @Override
    int hashCode() {
        return Objects.hash(this.id, this.artifact, this.deployments, this.service)
    }

    /**
     * Stringfy the flow object
     *
      * @return String representation of flow
     */
    @Override
    String toString() {
        return "id = ${id}, artifact: ${artifact}, deployments: ${deployments}, service: ${service}"
    }

}
