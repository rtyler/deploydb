package deploydb.cucumber

import deploydb.models.Artifact

class ModelHelper {

    Artifact sampleArtifact() {
        return  new Artifact('com.example.cucumber',
                             'cucumber-artifact',
                             '1.0.1')
    }
}
