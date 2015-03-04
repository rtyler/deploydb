package deploydb.models

import deploydb.Status
import spock.lang.*

class PromotionResultSpec extends Specification {


    def "ensure promotionResult can be instantiated"() {
        when:
        def promotionResult = new PromotionResult()

        then:
        promotionResult instanceof PromotionResult
    }
}

class PromotionResultWithArgsSpec extends Specification {


    def "promotionResult's properties should be correct"() {
        given:
        PromotionResult promotionResult = new PromotionResult("jenkins-smoke",
                Status.IN_PROGRESS,
                "http://local.lookout.com/jenkins/job-id/2/results")

        expect:
        promotionResult.promotionIdent == 'jenkins-smoke'
        promotionResult.status == Status.IN_PROGRESS
        promotionResult.infoUrl == "http://local.lookout.com/jenkins/job-id/2/results"
    }

    def "equals passes for promotionResults with same info"() {
        given:
        PromotionResult firstPromotionResult = new PromotionResult("jenkins-smoke",
                Status.IN_PROGRESS,
                "http://local.lookout.com/jenkins/job-id/2/results")
        PromotionResult secondPromotionResult = new PromotionResult("jenkins-smoke",
                Status.IN_PROGRESS,
                "http://local.lookout.com/jenkins/job-id/2/results")

        expect:
        firstPromotionResult == secondPromotionResult
    }

    def "hash compare passes for promotionResults with same info"() {
        given:
        PromotionResult firstPromotionResult = new PromotionResult("jenkins-smoke",
                Status.IN_PROGRESS,
                "http://local.lookout.com/jenkins/job-id/2/results")
        PromotionResult secondPromotionResult = new PromotionResult("jenkins-smoke",
                Status.IN_PROGRESS,
                "http://local.lookout.com/jenkins/job-id/2/results")

        expect:
        firstPromotionResult.hashCode() == secondPromotionResult.hashCode()
    }

    def "equals fails for promotionResults with different info"() {
        given:
        PromotionResult firstPromotionResult = new PromotionResult("jenkins-smoke",
                Status.IN_PROGRESS,
                "http://local.lookout.com/jenkins/job-id/2/results")
        PromotionResult secondPromotionResult = new PromotionResult("jenkins-smoke",
                Status.FAILED,
                "http://local.lookout.com/jenkins/job-id/3/results")

        expect:
        firstPromotionResult != secondPromotionResult
    }

    def "hash compare fails for promotionResults with different info"() {
        given:
        PromotionResult firstPromotionResult = new PromotionResult("jenkins-smoke",
                Status.IN_PROGRESS,
                "http://local.lookout.com/jenkins/job-id/2/results")
        PromotionResult secondPromotionResult = new PromotionResult("jenkins-smoke",
                Status.FAILED,
                "http://local.lookout.com/jenkins/job-id/3/results")

        expect:
        firstPromotionResult.hashCode() != secondPromotionResult.hashCode()
    }
}
