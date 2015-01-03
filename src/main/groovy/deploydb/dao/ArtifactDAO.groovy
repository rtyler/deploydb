package deploydb.dao

import com.google.common.base.Optional
import groovy.transform.InheritConstructors
import io.dropwizard.hibernate.AbstractDAO
import org.hibernate.criterion.*

import deploydb.models.Artifact

/**
 * Artifact Data access object
 */
@InheritConstructors
class ArtifactDAO extends AbstractDAO<Artifact> {

    /**
     * Locate a Artifact based on the artifactId passed in
     */
    Artifact findById(Long artifactId) {
        return get(artifactId)
    }

    /**
     * Locate an Artifact based on the (group, name) pair
     *
     * @param group A valid group name (e.g. "com.example")
     * @param name The artifact's name (e.g. "dropwizard-core")
     */
    Artifact findByGroupAndName(String group, String name) {
        List artifacts = criteria().add(Restrictions.eq('group', group))
                          .add(Restrictions.eq('name', name))
                          .setMaxResults(1)
                          .addOrder(Order.desc('createdAt')).list()
        if (artifacts.size() > 0) {
            return artifacts.first()
        }
        return null
    }

    Artifact create(Artifact artifact) {
        return persist(artifact)
    }
}
