import cucumber.api.*
import deploydb.ModelLoader
import deploydb.models.Pipeline.Pipeline

this.metaClass.mixin(cucumber.api.groovy.EN)

import deploydb.registry.ModelRegistry

Given(~/^a pipeline configuration name "(.*?)":$/) { String ident, String configBody ->
    withPipelineRegistry { ModelRegistry<Pipeline> pipelineRegistry ->
        ModelLoader<Pipeline> pipelineLoader = new ModelLoader<Pipeline>(Pipeline.class)
        Pipeline pipeline = pipelineLoader.loadFromString(configBody)
        pipeline.ident = ident

        pipelineRegistry.put(pipeline.ident, pipeline)
    }
}