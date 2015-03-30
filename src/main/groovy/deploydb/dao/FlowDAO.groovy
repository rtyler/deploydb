package deploydb.dao

import deploydb.models.Flow
import deploydb.Status
import groovy.transform.InheritConstructors
import io.dropwizard.hibernate.AbstractDAO
import org.hibernate.criterion.Projections
import org.hibernate.criterion.Restrictions


/**
 * Class to represent Data Access Object for Flow
 */
@InheritConstructors
class FlowDAO extends AbstractDAO<Flow> {
    /**
     * Fetch active flows count
     */
    Long getActiveFlowsCount() {
        return criteria()
                .add(Restrictions.and(
                Restrictions.ne("status", Status.SUCCESS),
                Restrictions.ne("status", Status.FAILED)))
                .setProjection(Projections.rowCount()).uniqueResult()
    }
}
