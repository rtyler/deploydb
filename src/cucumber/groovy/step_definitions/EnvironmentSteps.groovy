import cucumber.api.*

this.metaClass.mixin(cucumber.api.groovy.EN)

import deploydb.models.Environment
import deploydb.registry.ModelRegistry

Given(~/^there is an environment$/) { ->
    withEnvironmentRegistry { ModelRegistry<Environment> environmentRegistry ->
        Environment a = sampleEnvironment1(environmentRegistry)
    }
}
Given(~/^there are environments$/) { ->
    withEnvironmentRegistry { ModelRegistry<Environment> environmentRegistry ->
        Environment s1 = sampleEnvironment1(environmentRegistry)
        Environment s2 = sampleEnvironment2(environmentRegistry)        
    }
}
