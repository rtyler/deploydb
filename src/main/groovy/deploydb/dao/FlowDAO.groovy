package deploydb.dao

import groovy.transform.InheritConstructors

import deploydb.models.Flow
import io.dropwizard.hibernate.AbstractDAO


/**
 * Class to represent Data Access Object for Flow
 */
@InheritConstructors
class FlowDAO extends AbstractDAO<Flow> {
}
