package deploydb.models

import javax.persistence.Column
import javax.persistence.Id
import javax.persistence.MappedSuperclass
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

import com.fasterxml.jackson.annotation.JsonProperty
import org.joda.time.DateTime

@MappedSuperclass
abstract class AbstractModel {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id

    @Column(name='createdAt', nullable=false)
    protected DateTime createdAt = DateTime.now()

    @Column(name='deletedAt')
    protected DateTime deletedAt

    /**
     * The internal identifier for the artifact
     */
    @JsonProperty
    Long getId() {
        return this.id
    }

    @JsonProperty
    String getCreatedAt() {
        /* toString on DateTime returns ISO-8601 by default */
        return createdAt.toString()
    }

    void setCreatedAt(String iso8601String) {
        this.createdAt = DateTime.parse(iso8601String)
    }
}
