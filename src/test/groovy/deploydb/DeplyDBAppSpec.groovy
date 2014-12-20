package deploydb

import spock.lang.*

class DeployDBAppSpec extends Specification {

    def "ensure it can be instantiated"() {
        when:
        def app = new DeployDBApp()

        then:
        app instanceof DeployDBApp
        app.name == 'deploydb'
    }
}
