Feature: Artifact CREATE APIs

  As a RESTful client or user
  I should be able to create new artifacts in the system


  @freezetime
  Scenario: Creating a new artifact

    The creation of an artifact requires group, name, version and sourceUrl.

    When I PUT to "/api/v1/artifacts" with:
    """
      {
        "group" : "com.example.cucumber",
        "name" : "cukes",
        "version" : "1.2.345",
        "sourceUrl" : "http://example.com/cucumber.jar"
      }
    """
    Then the response should be 201
    And the body should be JSON:
    """
      {
        "id" : 1,
        "group" : "com.example.cucumber",
        "name" : "cukes",
        "version" : "1.2.345",
        "sourceUrl" : "http://example.com/cucumber.jar",
        "createdAt" : "{{created_timestamp}}"
      }
    """

  @error 
  Scenario: The reuqest should fail when creating an Artifact without a version

    When I PUT to "/api/v1/artifacts" with:
    """
      {
        "group" : "com.example.cucumber",
        "name" : "cukes",
        "sourceUrl" : "http://example.com/cucumber.jar"
      }
    """
    Then the response should be 422

  @wip @error
  Scenario: Creating an artifact with too large of a group or name

    Given an artifact with a name over 8192 characters
    When I POST to "/api/v1/artifacts" with it
    Then the response should be 400

  @error
  Scenario: The request should fail when creating an Artifact without a sourceUrl

    When I PUT to "/api/v1/artifacts" with:
    """
      {
        "group" : "com.example.cucumber",
        "name" : "cukes",
        "version" : "1.2.345"
      }
    """
    Then the response should be 422
