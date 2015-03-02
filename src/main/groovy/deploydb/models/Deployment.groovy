package deploydb.models

import com.fasterxml.jackson.annotation.JsonIgnore

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Enumerated
import javax.persistence.EnumType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToOne
import javax.persistence.Table
import javax.persistence.CascadeType;
import javax.persistence.FetchType

import com.fasterxml.jackson.annotation.JsonProperty

enum DeploymentStatus {STARTED, COMPLETED, FAILED}

/**
 * Representation class for the concept of a Deployment
 *
 */
@Entity
@Table(name='deployments')
class Deployment extends AbstractModel {

    @OneToOne(optional=false)
    @JoinColumn(name='artifactId', unique=false, nullable=false, updatable=false)
    @JsonProperty
    Artifact artifact

    @Column(name="environment", nullable=false)
    @JsonProperty
    String environment

    @Column(name="status", nullable=false)
    @Enumerated(EnumType.ORDINAL)
    @JsonProperty
    DeploymentStatus status = DeploymentStatus.STARTED

    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="flowId")
    @JsonIgnore
    Flow flow

    /**
     * Empty constructor used by Jackson for object deserialization
     */
    Deployment() { }

    /**
     * Default constructor to create a valid and saveable Deployment object in
     * the database
     */
    Deployment(Artifact deployedArtifact, String environment) {
        this.artifact = deployedArtifact
        this.environment = environment
    }

    @Override
    public boolean equals(Object o) {
        /* First object identity */
        if (this.is(o)) {
            return true
        }

        if (!(o instanceof Deployment)) {
            return false
        }

        final Deployment that = (Deployment)o

        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.artifact, that.artifact) &&
                Objects.equals(this.environment, that.environment) &&
                Objects.equals(this.status, that.status) &&
                Objects.equals(this.flow.id, that.flow.id)
    }

    @Override
    int hashCode() {
        return Objects.hash(this.id, this.artifact, this.environment,
                this.status, this.flow.id)
    }


    @Override
    String toString() {
        String output
        output += "id = ${id}, artifact: ${artifact}, environment: ${environment}, stauts: ${status}"
        output += "flow = ${flow.id}"
        return output
    }
}
