Feature: Artifact APIs

  As a RESTful client or user
  I should be able to read information about artifacts


  Scenario: Fetching an artifact by ID that exists

    Given there is an artifact
    When I GET "/api/v1/artifacts/1"
    Then the response should be 200
    And the body should be JSON:
    """
      {
        "id" : 1,
        "group" : "com.example.cucumber",
        "name" : "cucumber-artifact",
        "version" : "1.0.1",
        "sourceUrl" : "http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.1/cucumber-artifact-1.0.1.jar",
        "createdAt" : "{{created_timestamp}}"
      }
    """

  Scenario: Fetching an artifact by ID that doesn't exist

    When I GET "/api/v1/artifacts/1"
    Then the response should be 404


  @wip
  Scenario: Fetching an artifact by the name that exists

    Given there is an artifact named "cucumber-artifact"
    When I GET "/api/v1/artifacts/named/cucumber-artifact"
    Then the response should be 200

  Scenario: Fetching an artifact by the name that doesn't exist

    When I GET "/api/v1/artifacts/named/cucumber-artifact"
    Then the response should be 404
