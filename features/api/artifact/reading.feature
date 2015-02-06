Feature: Artifact READ APIs

  As a RESTful client or user
  I should be able to read information about artifacts


  @freezetime
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

  @error
  Scenario: Fetching an artifact by a non-numeric ID

    When I GET "/api/v1/artifacts/0xdeadbeef"
    Then the response should be 400


  @freezetime
  Scenario: Fetching an artifact by the (group, name) tuple

    Given there is an artifact
    When I GET "/api/v1/artifacts/by-module/com.example.cucumber:cucumber-artifact"
    Then the response should be 200
    And the body should be JSON:
    """
      [{
        "id" : 1,
        "createdAt" : "{{created_timestamp}}",
        "group" : "com.example.cucumber",
        "name" : "cucumber-artifact",
        "version" : "1.0.1",
        "sourceUrl" : "http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.1/cucumber-artifact-1.0.1.jar"
      }]
    """
@freezetime
Scenario: Fetching an artifact by the (group, name) tuple, pageNumber and perPageSize

    Given there is an artifact
    When I GET "/api/v1/artifacts/by-module/com.example.cucumber:cucumber-artifact?pageNumber=0&perPageSize=5"
    Then the response should be 200
    And the body should be JSON:
    """
      [{
        "id" : 1,
        "createdAt" : "{{created_timestamp}}",
        "group" : "com.example.cucumber",
        "name" : "cucumber-artifact",
        "version" : "1.0.1",
        "sourceUrl" : "http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.1/cucumber-artifact-1.0.1.jar"
      }]
    """
  Scenario: Fetching an artifact by the (group, name) tuple that doesn't exist

    When I GET "/api/v1/artifacts/by-module/com.example.cucumber:cucumber-artifact"
    Then the response should be 404

Scenario: Fetching an artifact by the (group, name) tuple with invalid page number

    When I GET "/api/v1/artifacts/by-module/com.example.cucumber:cucumber-artifact?pageNumber=1&perPageSize=5"
    Then the response should be 404


  @wip
  Scenario: Fetching an artifact's versions

    Given there is an artifact
    When I GET "/api/v1/artifacts/1/versions"
    Then the response should be 200

  @wip
  Scenario: Fetching the deployments for an artifact

    Given there is a deployment
    When I GET "/api/v1/artifacts/1/deployments"
    Then the response should be 200
