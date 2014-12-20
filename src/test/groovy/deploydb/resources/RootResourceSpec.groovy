package deploydb.resources

import spock.lang.*

class RootResourceSpec extends Specification {

    def "ensure it can be instantiated"() {
        when:
        def resource = new RootResource()

        then:
        resource instanceof RootResource
    }
}
