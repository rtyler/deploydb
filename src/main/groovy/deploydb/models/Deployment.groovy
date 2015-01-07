package deploydb.models

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Enumerated
import javax.persistence.EnumType
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length
import org.joda.time.DateTime


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
}
