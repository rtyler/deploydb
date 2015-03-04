Feature: Deployment UPDATE APIs

  As a RESTful client or user
  I should be able to update existing deployments in the system


  Scenario: Updating a deployment with a status STARTED

    Given there is a deployment
    When I PATCH "/api/deployments/1" with:
    """
      {
        "status" : "COMPLETED"
      }
    """
    Then the response should be 204


  Scenario: Updating a deployment with a status COMPLETED

    Given there is a deployment
    When I PATCH "/api/deployments/1" with:
    """
      {
        "status" : "FAILED"
      }
    """
    Then the response should be 204


  Scenario: Updating a deployment with invalid status transition

    Given there is a deployment
    When I PATCH "/api/deployments/1" with:
    """
      {
        "status" : "NOT_STARTED"
      }
    """
    Then the response should be 406


  @error
  Scenario: Updating a deployment with an malformed status

    Given there is a deployment
    When I PATCH "/api/deployments/1" with:
    """
      {
        "status" : "INVALID"
      }
    """
    Then the response should be 400


  @error
  Scenario: Updating a deployment that doesn't exist

    When I PATCH "/api/deployments/1" with:
    """
      {
        "status" : "FAILED"
      }
    """
    Then the response should be 404

