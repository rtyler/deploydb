package deploydb.dao

import groovy.transform.InheritConstructors
import io.dropwizard.hibernate.AbstractDAO

import deploydb.models.Deployment
import org.hibernate.criterion.Order

/**
 * Deployment Data access object
 */
@InheritConstructors
class DeploymentDAO extends AbstractDAO<Deployment> {

    /**
     * Locate an Deployment based on the (group, name) pair
     *
     * @param pageNumber A valid page number for pagination
     * @param perPageSize A valid per page size
     */
    List<Deployment> getByPage(int pageNumber, int perPageSize) {

        List<Deployment> deployments = criteria()
                .setFirstResult(pageNumber)
                .setMaxResults(perPageSize)
                .addOrder(Order.desc('createdAt')).list()
        return deployments
    }

}
