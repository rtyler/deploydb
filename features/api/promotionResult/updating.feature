Feature: PromotionResult UPDATE APIs

  As a RESTful client or user
  I should be able to update promotionResult for the existing deployments in the system


  @wip
  Scenario: Updating the PromotionResult for a deployment

    Given there is a deployment
    When I PUT "/api/deployments/1/promotions" with:
    """
      {
        "name"  : "jenkins-smoke",
        "status" : "SUCCESS",
        "resultsUrl" : "http://local.lookout.com/jenkins/job-id/2/results"
      }
    """
    Then the response should be 204


  @wip
  Scenario: Updating the PromotionResult for deployment that doesn't exist

    When I PUT "/api/deployments/1/promotions" with:
    """
      {
        "name"  : "jenkins-smoke",
        "status" : "SUCCESS",
        "resultsUrl" : "http://local.lookout.com/jenkins/job-id/2/results"
      }
    """
    Then the response should be 404


  @wip @error
  Scenario: Updating the PromotionResult for promotion that doesn't exist in deployment

    When I PUT "/api/deployments/1/promotions" with:
    """
      {
        "name"  : "test-smoke",
        "status" : "FAILED",
        "resultsUrl" : "http://local.lookout.com/jenkins/job-id/2/results"
      }
    """
    Then the response should be 404
    