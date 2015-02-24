import cucumber.api.*
import deploydb.ModelLoader
import deploydb.models.Promotion

this.metaClass.mixin(cucumber.api.groovy.EN)

import deploydb.registry.ModelRegistry

Given(~/^a promotion configuration name "(.*?)":$/) { String ident, String configBody ->
    withPromotionRegistry { ModelRegistry<Promotion> promotionRegistry ->
        ModelLoader<Promotion> promotionLoader = new ModelLoader<Promotion>(Promotion.class)
        Promotion promotion = promotionLoader.loadFromString(configBody)
        promotion.ident = ident

        promotionRegistry.put(promotion.ident, promotion)
    }
}