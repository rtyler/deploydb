import cucumber.api.*

this.metaClass.mixin(cucumber.api.groovy.EN)

import deploydb.models.Service
import deploydb.registry.ModelRegistry

Given(~/^there is an service$/) { ->
    withServiceRegistry { ModelRegistry<Service> serviceRegistry ->
        Service a = sampleService1(serviceRegistry)
    }
}
Given(~/^there are services$/) { ->
    withServiceRegistry { ModelRegistry<Service> serviceRegistry ->
        Service s1 = sampleService1(serviceRegistry)
        Service s2 = sampleService2(serviceRegistry)        
    }
}
