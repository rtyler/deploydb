Feature: Deployment UPDATE APIs

  As a RESTful client or user
  I should be able to update existing deployments in the system


  @freezetime @wip
  Scenario: Updating a deployment with a status STARTED

    Given there is a deployment
    When I PATCH "/api/deployments/1" with:
    """
      {
        "status" : "COMPLETED"
      }
    """
    Then the response should be 200
    And the body should be JSON:
    """
      {
        "id" : 1,
        "artifact" : {
          "id" : 1,
          "group" : "com.example.cucumber",
          "name" : "cucumber-artifact",
          "version" : "1.0.1",
          "sourceUrl" : "http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.1/cucumber-artifact-1.0.1.jar",
          "createdAt" : "{{created_timestamp}}"
        },
        "environment" : "pre-prod",
        "service" : "faas",
        "status" : "COMPLETED",
        "promotions" : [{
          "id" : 1,
          "promotion" : "jenkins-smoke",
          "status" : "STARTED",
          "infoUrl" : null,
          "createdAt" : "{{created_timestamp}}"
         }],
        "createdAt" : "{{created_timestamp}}"
      }
    """


  @freezetime @wip
  Scenario: Updating a deployment with a status COMPLETED

    Given there is a deployment
    When I PATCH "/api/deployments/1" with:
    """
      {
        "status" : "FAILED"
      }
    """
    Then the response should be 200
    And the body should be JSON:
    """
      {
        "id" : 1,
        "artifact" : {
          "id" : 1,
          "group" : "com.example.cucumber",
          "name" : "cucumber-artifact",
          "version" : "1.0.1",
          "sourceUrl" : "http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.1/cucumber-artifact-1.0.1.jar",
          "createdAt" : "{{created_timestamp}}"
        },
        "environment" : "pre-prod",
        "service" : "faas",
        "status" : "FAILED",
        "promotions" : [{
          "id" : 1,
          "promotion" : "jenkins-smoke",
          "status" : "STARTED",
          "infoUrl" : null,
          "createdAt" : "{{created_timestamp}}"
         }],
        "createdAt" : "{{created_timestamp}}"
      }
    """


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

