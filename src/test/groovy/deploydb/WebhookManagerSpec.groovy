package deploydb

import spock.lang.*

class WebhookManagerSpec extends Specification {
    def "ensure constructor works"() {
        given:
        WebhookManager m

        when:
        m = new WebhookManager()

        then:
        m instanceof WebhookManager
    }

    def "running() should return false by default"() {
        given:
        WebhookManager m = new WebhookManager()

        expect:
        m.running == false
    }
}
