package deploydb.models

import deploydb.ModelLoader
import deploydb.registry.ModelRegistry
import io.dropwizard.configuration.ConfigurationParsingException
import spock.lang.Specification

class PromotionSpec extends Specification {
    def "ensure Promotion object can be instantiated"() {
        when:
        def promotion = new Promotion()

        then:
        promotion instanceof Promotion
    }
}

class PromotionWithArgsSpec extends Specification {
    private final ModelRegistry<Promotion> promotionRegistry =
            new ModelRegistry<Promotion>()
    private final ModelLoader<Promotion> promotionLoader =
            new ModelLoader<Promotion>(Promotion.class)

    def "Loading of valid promotion config content succeeds"() {
        given:
        Promotion promotion = promotionLoader.loadFromString("type: BasicPromotion\n" +
                "description: Smoke test the Fun as a Service service\n")
        promotion.ident = "basic"
        promotionRegistry.put(promotion.ident, promotion)

        expect:
        promotion.ident == "basic"
        promotion.type == 'BasicPromotion'
        promotion.description == "Smoke test the Fun as a Service service"
        promotionRegistry.get("basic") == promotion
        promotionRegistry.getAll()== [promotion]
    }

    def "Loading of empty model promotion throws a null pointer exception"(){
        when:
        Promotion promotion = promotionLoader.loadFromString("")

        then:
        thrown(NullPointerException)
    }

    def "Loading a malformed promotion throws a parsing exception"(){
        when:
        Promotion promotion = promotionLoader.loadFromString("type")

        then:
        thrown(ConfigurationParsingException)
    }

    def "Loading a empty model promotion config succeeds"(){
        given:
        Promotion promotion = promotionLoader.loadFromString("type:\n"+
                  "description:\n")

        expect:
        promotion.type == ""
        promotion.description == ""
    }

}