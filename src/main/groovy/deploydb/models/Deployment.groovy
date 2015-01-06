package deploydb.models

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.OneToOne

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length
import org.joda.time.DateTime

/**
 * Representation class for the concept of a Deployment
 *
 */
@Entity
@Table(name='deployments')
class Deployment extends AbstractModel {

    @OneToOne
    @Column(name='artifactId')
    private Artifact artifact

    @Column(name="environment")
    private String environment

    /**
     * Empty constructor used by Jackson for object deserialization
     */
    Deployment() { }

}
