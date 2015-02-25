package deploydb.cucumber

import deploydb.models.Artifact

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
}
