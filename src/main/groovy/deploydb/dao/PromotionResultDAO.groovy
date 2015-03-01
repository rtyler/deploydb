package deploydb.dao

import groovy.transform.InheritConstructors
import io.dropwizard.hibernate.AbstractDAO

import deploydb.models.PromotionResult
import org.hibernate.criterion.Order

/**
 * PromotionResult Data access object
 */
@InheritConstructors
class PromotionResultDAO extends AbstractDAO<PromotionResult> {

    /**
     * Locate an PromotionResult based on the (group, name) pair
     *
     * @param pageNumber A valid page number for pagination
     * @param perPageSize A valid per page size
     */
    List<PromotionResult> getByPage(int pageNumber, int perPageSize) {

        List<PromotionResult> promotionResults = criteria()
                .setFirstResult(pageNumber)
                .setMaxResults(perPageSize)
                .addOrder(Order.desc('createdAt')).list()
        return promotionResults
    }

}
