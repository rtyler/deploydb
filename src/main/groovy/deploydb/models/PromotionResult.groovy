package deploydb.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import deploydb.Status
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Enumerated
import javax.persistence.EnumType
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table
import org.assertj.core.internal.Comparables
import org.hibernate.validator.constraints.NotEmpty


/**
 * Representation class for the concept of a Deployment
 */
@Entity
@Table(name='promotionResults')
class PromotionResult extends AbstractModel {

    @NotEmpty
    @Column(name="promotion", nullable=false)
    @JsonProperty(value = "promotion")
    String promotionIdent

    @Column(name="status", nullable=false)
    @Enumerated(EnumType.ORDINAL)
    @JsonProperty
    Status status = Status.NOT_STARTED

    @Column(name="infoUrl")
    @JsonProperty
    String infoUrl

    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL) //  optional (targetEntity = Deployment.class)
    @JoinColumn(name="deploymentId") // column name in promotionResults table; if not specified assumes deployment_id as column name
    @JsonIgnore
    Deployment deployment

    /**
     * Empty constructor used by Jackson for object deserialization
     */
    PromotionResult() { }

    /**
     * Default constructor to create a valid and saveable Deployment object in
     * the database
     */
    PromotionResult(String promotionIdent,
               Status status,
               String infoUrl) {
        this.promotionIdent = promotionIdent
        this.status = status
        this.infoUrl = infoUrl
    }

    @Override
    public boolean equals(Object o) {
        /* First object identity */
        if (this.is(o)) {
            return true
        }

        if (!(o instanceof PromotionResult)) {
            return false
        }

        final PromotionResult that = (PromotionResult)o

        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.promotionIdent, that.promotionIdent) &&
                Objects.equals(this.status, that.status) &&
                Objects.equals(this.infoUrl, that.infoUrl) &&
                Objects.equals(this.deployment.id, that.deployment.id)
    }

    @Override
    int hashCode() {
        int val = Objects.hash(this.id, this.promotionIdent, this.status,
                this.infoUrl, this.deployment.id)
        return val
    }

    @Override
    String toString() {
        String output = ""
        output += "id = ${this.id}, promotion: ${promotionIdent}, status: ${status}, "
        output += "infoUrl = ${infoUrl}, deployment: $deployment.id"
        return output
    }
}
