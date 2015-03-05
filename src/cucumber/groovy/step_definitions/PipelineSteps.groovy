this.metaClass.mixin(cucumber.api.groovy.EN)

import deploydb.ModelLoader
import deploydb.models.pipeline.Pipeline
import deploydb.registry.ModelRegistry

Given(~/^a pipeline configuration named "(.*?)":$/) { String ident, String configBody ->
    withPipelineRegistry { ModelRegistry<Pipeline> pipelineRegistry ->
        ModelLoader<Pipeline> pipelineLoader = new ModelLoader<Pipeline>(Pipeline.class)
        Pipeline pipeline = pipelineLoader.loadFromString(configBody)
        pipeline.ident = ident

        pipelineRegistry.put(pipeline.ident, pipeline)
    }
}

Given(~/^a pipeline is configured$/) { ->
    withPipelineRegistry { ModelRegistry<Pipeline> pipelineRegistry ->
        Pipeline a = samplePipeline1()
        pipelineRegistry.put(a.ident, a)
    }
}