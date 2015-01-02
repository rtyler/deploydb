Feature: Deployment Artifact APIs

  As a RESTful client or user
  I should be able to create deployments
  And I should be able to read about them


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
          "version" : "1.0.1"
        },
        "environment" : {
          "name" : "pre-production"
        },
        "service" : {
          "id" : 1
        },
        "createdAt" : "{{created_timestamp}}"
      }
    """
