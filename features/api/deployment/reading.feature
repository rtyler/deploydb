Feature: Deployment READ APIs

  As a RESTful client or user
  I should be able to read information about deployments


  @freezetime
  Scenario: Fetching a deployment by ID

    Given there is a deployment
    When I GET "/api/v1/deployments/1"
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
        "status" : "STARTED",
        "createdAt" : "{{created_timestamp}}"
      }
    """

  Scenario: Fetching a deployment by ID that doesn't exist

    When I GET "/api/v1/deployments/1"
    Then the response should be 404
