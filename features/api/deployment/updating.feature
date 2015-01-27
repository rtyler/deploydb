Feature: Deployment UPDATE APIs

  As a RESTful client or user
  I should be able to update existing deployments in the system


  @freezetime @wip
  Scenario: Updating a deployment with a status change

    Given there is a deployment
    When I PATCH "/api/v1/deployments/1" with:
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
        "environment" : "pre-production",
        "status" : "FAILED",
        "createdAt" : "{{created_timestamp}}"
      }
    """


  @freezetime @error
  Scenario: Updating a deployment that doesn't exist

    When I PATCH "/api/v1/deployments/1" with:
    """
      {
        "status" : "FAILED"
      }
    """
    Then the response should be 404
