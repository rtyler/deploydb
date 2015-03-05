this.metaClass.mixin(cucumber.api.groovy.EN)

import deploydb.models.Service
import deploydb.registry.ModelRegistry
import deploydb.ModelLoader

Given(~/^a service configuration named "(.*?)":$/) { String serviceIdent, String cfgBody ->
    withServiceRegistry { ModelRegistry<Service> serviceRegistry ->
            ModelLoader<Service> serviceLoader = new ModelLoader<>(Service.class)
            Service a = serviceLoader.loadFromString(cfgBody)
            a.ident = serviceIdent
            serviceRegistry.put(serviceIdent, a)
    }
}

Given(~/^a service is configured$/) { ->
    withServiceRegistry { ModelRegistry<Service> serviceRegistry ->
            Service a = sampleService1()
            serviceRegistry.put(a.ident, a)
    }
}
