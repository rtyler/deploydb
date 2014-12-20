package deploydb

import spock.lang.*

class DeployDBConfigurationSpec extends Specification {

    def "ensure it can be instantiated"() {
        when:
        def config = new DeployDBConfiguration()

        then:
        config instanceof DeployDBConfiguration
    }
}
