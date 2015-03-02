Feature: Promotion Result APIs

  As a RESTful client or user
  I should be able to add Promotion result for a Deployment in the system


  @wip
  Scenario: Adding a result for a Promotion associated with a Deployment

    Given there is a deployment
    When I POST to "/api/deployments/1/promotions" with:
    """
      {
        "name"  : "jenkins-smoke",
        "status" : "SUCCESS",
        "infoUrl" : "http://local.lookout.com/jenkins/job-id/2/results"
      }
    """
    Then the response should be 204


  @wip
  Scenario: Adding Promotion result for a Deployment that doesn't exist

    When I POST to "/api/deployments/1/promotions" with:
    """
      {
        "name"  : "jenkins-smoke",
        "status" : "SUCCESS",
        "infoUrl" : "http://local.lookout.com/jenkins/job-id/2/results"
      }
    """
    Then the response should be 404


  @wip @error
  Scenario: Adding a result for a Promotion which is not associated with the Deployment

    When I POST to "/api/deployments/1/promotions" with:
    """
      {
        "name"  : "test-smoke",
        "status" : "FAILED",
        "infoUrl" : "http://local.lookout.com/jenkins/job-id/2/results"
      }
    """
    Then the response should be 404
