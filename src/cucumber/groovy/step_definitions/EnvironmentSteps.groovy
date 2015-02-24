import cucumber.api.*

this.metaClass.mixin(cucumber.api.groovy.EN)

import deploydb.models.Environment
import deploydb.registry.ModelRegistry
import deploydb.ModelLoader

Given(~/^an environment configuration named "(.*?)":$/) { String envIdent, String cfgBody ->
    withEnvironmentRegistry {
        ModelRegistry<Environment> environmentRegistry,
        ModelLoader<Environment> environmentLoader ->
        Environment a = environmentLoader.loadFromString(cfgBody)
        a.ident = envIdent
        environmentRegistry.put(envIdent, a)
    }
}
