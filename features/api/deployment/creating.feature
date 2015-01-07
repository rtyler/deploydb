Feature: Deployment CREATE APIs

  As a RESTful client or user
  I should be able to create new deployments in the system


  @freezetime
  Scenario: Creating a new deployment

    Creating a deployment assumes that we've already got an Artifact stored
    inside the DeployDB database

    Given there is an artifact
    When I PUT to "/api/v1/deployments" with:
    """
      {
        "artifactId" : 1,
        "environment" : "pre-production"
      }
    """
    Then the response should be 201
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

  @freezetime
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
        "status" : "failed",
        "createdAt" : "{{created_timestamp}}"
      }
    """

