package deploydb.models

import deploydb.Status
import spock.lang.*

class DeploymentSpec extends Specification {


    def "ensure deployment can be instantiated"() {
        when:
        def deployment = new Deployment()

        then:
        deployment instanceof Deployment
    }
}

class DeploymentWithArgsSpec extends Specification {


    def "deployment's properties should be correct"() {
        given:

        Artifact a1 = new Artifact('spock.group',
                                   'spock-test-name',
                                   'v1.0.0',
                                   'http://example.com/cucumber.jar')
        PromotionResult p1 = new PromotionResult("jenkins-smoke", Status.CREATED, "")
        Deployment d1 = new Deployment(a1,
                "pre-prod",
                "faas",
                Status.STARTED)
        d1.addPromotionResult(p1)

        expect:
        d1.artifact == a1
        d1.status == Status.STARTED
        d1.environmentIdent == "pre-prod"
        d1.serviceIdent == "faas"
        d1.promotionResultSet[0] == p1
    }

    def "equals passes for deployments with same info"() {
        given:
        Artifact a1 = new Artifact('spock.group',
                'spock-test-name',
                'v1.0.0',
                'http://example.com/cucumber.jar')
        PromotionResult p1 = new PromotionResult("jenkins-smoke", Status.CREATED, "")
        Deployment d1 = new Deployment(a1,
                "pre-prod",
                "faas",
                Status.STARTED)
        d1.addPromotionResult(p1)

        Artifact a2 = new Artifact('spock.group',
                'spock-test-name',
                'v1.0.0',
                'http://example.com/cucumber.jar')
        PromotionResult p2 = new PromotionResult("jenkins-smoke", Status.CREATED, "")
        Deployment d2 = new Deployment(a1,
                "pre-prod",
                "faas",
                Status.STARTED)
        d2.addPromotionResult(p2)

        expect:
        d1 == d2
    }

    def "hashCode compare passes for deployments with same info"() {
        given:
        Artifact a1 = new Artifact('spock.group',
                'spock-test-name',
                'v1.0.0',
                'http://example.com/cucumber.jar')
        PromotionResult p1 = new PromotionResult("jenkins-smoke", Status.CREATED, "")
        Deployment d1 = new Deployment(a1,
                "pre-prod",
                "faas",
                Status.STARTED)
        d1.addPromotionResult(p1)

        Artifact a2 = new Artifact('spock.group',
                'spock-test-name',
                'v1.0.0',
                'http://example.com/cucumber.jar')
        PromotionResult p2 = new PromotionResult("jenkins-smoke", Status.CREATED, "")
        Deployment d2 = new Deployment(a1,
                "pre-prod",
                "faas",
                Status.STARTED)
        d2.addPromotionResult(p2)

        expect:
        d1.hashCode() == d2.hashCode()
    }

    def "equals fails for deployments with different artifacts"() {
        given:
        Artifact a1 = new Artifact('spock.group',
                'spock-test-name',
                'v1.0.0',
                'http://example.com/cucumber.jar')
        Deployment d1 = new Deployment(a1,
                "pre-prod",
                "faas",
                Status.STARTED)

        Artifact a2 = new Artifact('spock.group',
                'spock-test-name2',
                'v1.0.1',
                'http://example.com/cucumber.jar')
        Deployment d2 = new Deployment(a2,
                "pre-prod",
                "faas",
                Status.STARTED)

        expect:
        d1 != d2
    }

    def "equals fails for deployments with different promotionResults"() {
        given:
        PromotionResult p1 = new PromotionResult("jenkins-smoke", Status.CREATED, "")
        Deployment d1 = new Deployment()
        d1.addPromotionResult(p1)

        PromotionResult p2 = new PromotionResult("jenkins-smoke", Status.FAILED, "")
        Deployment d2 = new Deployment()
        d2.addPromotionResult(p2)

        expect:
        d1 != d2
    }

    def "hashCode compare fails for deployments with different artifacts"() {
        given:
        Artifact a1 = new Artifact('spock.group',
                'spock-test-name',
                'v1.0.0',
                'http://example.com/cucumber.jar')
        Deployment d1 = new Deployment(a1,
                "pre-prod",
                "faas",
                Status.STARTED)

        Artifact a2 = new Artifact('spock.group',
                'spock-test-name2',
                'v1.0.1',
                'http://example.com/cucumber.jar')
        Deployment d2 = new Deployment(a2,
                "pre-prod",
                "faas",
                Status.STARTED)

        expect:
        d1.hashCode() != d2.hashCode()
    }

    def "hashCode compare fails for deployments with different promotionResults"() {
        given:
        PromotionResult p1 = new PromotionResult("jenkins-smoke", Status.CREATED, "")
        Deployment d1 = new Deployment()
        d1.addPromotionResult(p1)

        PromotionResult p2 = new PromotionResult("jenkins-smoke", Status.FAILED, "")
        Deployment d2 = new Deployment()
        d2.addPromotionResult(p2)

        expect:
        d1.hashCode() != d2.hashCode()
    }
}
