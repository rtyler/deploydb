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

