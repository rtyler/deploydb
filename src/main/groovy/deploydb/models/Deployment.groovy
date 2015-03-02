package deploydb.models

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonIgnore
import deploydb.Status
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Enumerated
import javax.persistence.EnumType
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.ManyToOne
import javax.persistence.OneToOne
import javax.persistence.OrderBy
import javax.persistence.Table
import org.eclipse.jetty.util.ConcurrentHashSet


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
    @JsonProperty(value = "environment")
    String environmentIdent

    @Column(name="service", nullable=false)
    @JsonProperty(value = "service")
    String serviceIdent

    @Column(name="status", nullable=false)
    @Enumerated(EnumType.ORDINAL)
    @JsonProperty
    Status status = Status.NOT_STARTED

    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="deployment")
    @JsonProperty
    @OrderBy(value="id")
    Set<PromotionResult> promotionResultSet = new ConcurrentHashSet<>()

    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="flowId")
    @JsonIgnore
    Flow flow

    /**
     * Add Promotion Result to collection
     */
    void addPromotionResult(PromotionResult promotionResult) {
        promotionResult.deployment = this
        promotionResultSet.add(promotionResult)
    }

    /**
     * Empty constructor used by Jackson for object deserialization
     */
    Deployment() { }

    /**
     * Default constructor to create a valid and saveable Deployment object in
     * the database
     */
    Deployment(Artifact artifact,
               String environmentIdent,
               String serviceIdent,
               Status status) {
        this.artifact = artifact
        this.environmentIdent = environmentIdent
        this.serviceIdent = serviceIdent
        this.status = status
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
                Objects.equals(this.environmentIdent, that.environmentIdent) &&
                Objects.equals(this.serviceIdent, that.serviceIdent) &&
                Objects.equals(this.status, that.status) &&
                Objects.equals(this.promotionResultSet, that.promotionResultSet)
    }

    @Override
    int hashCode() {
        return Objects.hash(this.id, this.artifact, this.environmentIdent,
                this.serviceIdent, this.status, this.promotionResultSet)
    }

    @Override
    String toString() {
        String output = ""
        output += "id = ${id}, environment: ${environmentIdent}, "
        output += "service = ${serviceIdent}, status: ${status}, "
        output += "promotionResultSet: ${promotionResultSet}"
        output += "flow = ${flow.id}"
        return output
    }
}
