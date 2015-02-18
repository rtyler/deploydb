package deploydb.cucumber

import deploydb.models.Artifact
import deploydb.models.Service
import deploydb.resources.ModelRegistry

class ModelHelper {

    Artifact sampleArtifact() {
        return  new Artifact('com.example.cucumber',
                             'cucumber-artifact',
                             '1.0.1',
                             'http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.1/cucumber-artifact-1.0.1.jar')
    }
    Artifact sampleArtifactV2() {
        return  new Artifact('com.example.cucumber',
                             'cucumber-artifact',
                             '1.0.2',
                             'http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.2/cucumber-artifact-1.0.2.jar')
    }    

    /**
     * Creates a sample service object
     */ 
    Service sampleService1(ModelRegistry<Service> serviceRegistry) {
        Service service = new Service('Fun-as-a-Service',
                                      [ 'com.github.lookout:foas',
                                        'com.github.lookout.puppet:puppet-foas',
                                        'com.github.lookout:puppet-mysql' ],
                                      [ 'detoprod' ],
                                      [ 'status-check',
                                        'jenkins-smoke' ])
        serviceRegistry.insert(service)
        return service
    }

    /**
     * Creates a sample service object
     */ 
    Service sampleService2(ModelRegistry<Service> serviceRegistry) {
        Service service = new Service('Auditlog-as-a-Service',
                                      [ 'com.github.lookout:alas',
                                        'com.github.lookout.puppet:puppet-alas',
                                        'com.github.lookout:puppet-mysql' ],
                                      [ 'detoprod' ],
                                      [ 'status-check',
                                        'jenkins-smoke' ])
        serviceRegistry.insert(service)
        return service}
}
