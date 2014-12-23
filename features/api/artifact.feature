Feature: Artifact APIs

  As a RESTful client or user
  I should be able to read information about artifacts


  Scenario: Fetching an artifact by ID that exists

    Given there is an artifact with ID 1
    When I GET "/api/v1/artifacts/1"
    Then the response should be 200

  @wip
  Scenario: Fetching an artifact by ID that doesn't exist

    When I GET "/api/v1/artifacts/1"
    Then the response should be 404


  Scenario: Fetching an artifact by the name that exists

    Given there is an artifact named "cucumber-artifact"
    When I GET "/api/v1/artifacts/named/cucumber-artifact"
    Then the response should be 200

  @wip
  Scenario: Fetching an artifact by the name that doesn't exist

    When I GET "/api/v1/artifacts/named/cucumber-artifact"
    Then the response should be 404
