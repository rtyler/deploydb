package deploydb

import spock.lang.*

class WorkFlowSpec extends Specification {

    def "ensure it can be instantiated"() {
        when:
        def app = new DeployDBApp()
        def workFlow = new WorkFlow(app)

        then:
        workFlow instanceof WorkFlow
    }
}
