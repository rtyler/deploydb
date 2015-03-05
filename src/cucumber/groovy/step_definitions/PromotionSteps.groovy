this.metaClass.mixin(cucumber.api.groovy.EN)

import deploydb.ModelLoader
import deploydb.models.Promotion
import deploydb.registry.ModelRegistry


Given(~/^a promotion configuration name "(.*?)":$/) { String ident, String configBody ->
    withPromotionRegistry { ModelRegistry<Promotion> promotionRegistry ->
        ModelLoader<Promotion> promotionLoader = new ModelLoader<>(Promotion.class)
        Promotion promotion = promotionLoader.loadFromString(configBody)
        promotion.ident = ident

        promotionRegistry.put(promotion.ident, promotion)
    }
}

Given(~/^a promotion is configured$/) { ->
    withPromotionRegistry { ModelRegistry<Promotion> promotionRegistry ->
            Promotion a = samplePromotion1()
            promotionRegistry.put(a.ident, a)
    }
}

Given(~/^promotions are configured$/) { ->
    withPromotionRegistry { ModelRegistry<Promotion> promotionRegistry ->
        Promotion a = samplePromotion1()
        promotionRegistry.put(a.ident, a)
        Promotion b = samplePromotion2()
        promotionRegistry.put(b.ident, b)
    }
}