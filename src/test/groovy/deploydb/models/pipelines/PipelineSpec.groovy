package deploydb.models.Pipeline

import deploydb.ModelLoader
import deploydb.registry.ModelRegistry
import io.dropwizard.configuration.ConfigurationParsingException
import spock.lang.Specification

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class PipelineSpec extends Specification {

    def "ensure Pipeline object can be instantiated"() {
        when:
        def pipeline = new Pipeline()

        then:
        pipeline instanceof Pipeline
    }
}

class PipelineWithArgsSpec extends Specification {
    private final ModelRegistry<Pipeline> pipelineRegistry =
            new ModelRegistry<Pipeline>()
    private final ModelLoader<Pipeline> pipelineLoader =
            new ModelLoader<Pipeline>(Pipeline.class)
    private final Logger logger = LoggerFactory.getLogger(PipelineWithArgsSpec.class)


    def "Loading of valid pipeline config content succeeds"() {
        given:
        Pipeline pipeline = pipelineLoader.loadFromString("""
    description: "Development to production pipeline"
    environments:
        dev-alpha:
           promotions:
             -
        dev-beta:
           promotions:
             -
        integ:
           promotions:
             -
        preprod:
           promotions:
             - prod-preflight
             - manual
        prod:
           promotions:
             -
""")
        pipeline.ident = "devtoprod"
        pipelineRegistry.put(pipeline.ident, pipeline)

        expect:
        pipeline.ident == "devtoprod"
        pipeline.description == "Development to production pipeline"
        pipeline.environments['preprod'].promotions == ['prod-preflight', 'manual']
        pipelineRegistry.getAll() == [pipeline]
    }

    def "Loading of empty model pipeline throws a null pointer exception"() {
        when:
        Pipeline pipeline = pipelineLoader.loadFromString("")

        then:
        thrown(NullPointerException)
    }

    def "Loading a malformed pipeline throws a parsing exception"() {
        when:
        Pipeline pipeline = pipelineLoader.loadFromString("description")

        then:
        thrown(ConfigurationParsingException)
    }

    def "Loading a empty model pipeline config succeeds"() {
        given:
        Pipeline pipeline = pipelineLoader.loadFromString("description:\n")

        expect:
        pipeline.description == ""
    }
}