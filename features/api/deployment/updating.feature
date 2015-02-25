Feature: Deployment UPDATE APIs

  As a RESTful client or user
  I should be able to update existing deployments in the system


  @wip
  Scenario: Updating a deployment with a status STARTED

    Given there is a deployment
    When I PATCH "/api/deployments/1" with:
    """
      {
        "status" : "STARTED"
      }
    """
    Then the response should be 200


  @wip
  Scenario: Updating a deployment with a status COMPLETED

    Given there is a deployment
    When I PATCH "/api/deployments/1" with:
    """
      {
        "status" : "COMPLETED"
      }
    """
    Then the response should be 200


  @wip
  Scenario: Updating a deployment with a status FAILED

    Given there is a deployment
    When I PATCH "/api/deployments/1" with:
    """
      {
        "status" : "FAILED"
      }
    """
    Then the response should be 200


  @wip @error
  Scenario: Updating a deployment with an invalid status

    Given there is a deployment
    When I PATCH "/api/deployments/1" with:
    """
      {
        "status" : "INVALID"
      }
    """
    Then the response should be 400


  @wip @error
  Scenario: Updating a deployment that doesn't exist

    When I PATCH "/api/deployments/1" with:
    """
      {
        "status" : "FAILED"
      }
    """
    Then the response should be 404


  @wip
  Scenario: Updating the Promotion status for a deployment

    Given there is a deployment
    When I PUT "/api/deployments/1/promotions" with:
    """
      {
        "name"  : "jenkins-smoke",
        "status" : "SUCCESS",
        "resultsUrl" : "http://local.lookout.com/jenkins/job-id/2/results"
      }
    """
    Then the response should be 200


  @wip
  Scenario: Updating the Promotion status for deployment that doesn't exist

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
  Scenario: Updating the Promotion status for promotion that doesn't exist in deployment

    When I PUT "/api/deployments/1/promotions" with:
    """
      {
        "name"  : "test-smoke",
        "status" : "FAILED",
        "resultsUrl" : "http://local.lookout.com/jenkins/job-id/2/results"
      }
    """
    Then the response should be 404

