Feature: Artifact CREATE APIs

  As a RESTful client or user
  I should be able to create new artifacts in the system


  @freezetime
  Scenario: Creating a new artifact

    The creation of an artifact requires group, name, version and sourceUrl.

    When I POST to "/api/artifacts" with:
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
  Scenario: The request should fail when creating an Artifact without a group name

    When I POST to "/api/artifacts" with:
    """
      {
        "name" : "cukes",
        "version" : "1.2.345",
        "sourceUrl" : "http://example.com/cucumber.jar"
      }
    """
    Then the response should be 422

  @error
  Scenario: The request should fail when creating an Artifact without a name

    When I POST to "/api/artifacts" with:
    """
      {
        "group" : "com.example.cucumber",
        "version" : "1.2.345",
        "sourceUrl" : "http://example.com/cucumber.jar"
      }
    """
    Then the response should be 422

  @error
  Scenario: The request should fail when creating an Artifact without a version

    When I POST to "/api/artifacts" with:
    """
      {
        "group" : "com.example.cucumber",
        "name" : "cukes",
        "sourceUrl" : "http://example.com/cucumber.jar"
      }
    """
    Then the response should be 422

  @error
  Scenario: The request should fail when creating an Artifact without a sourceUrl

    When I POST to "/api/artifacts" with:
    """
      {
        "group" : "com.example.cucumber",
        "name" : "cukes",
        "version" : "1.2.345"
      }
    """
    Then the response should be 422

  @error
  Scenario: Creating an artifact with too large of a group name

    Given I have an artifact request
    And the group is over 8192 characters
    When I POST to "/api/artifacts"
    Then the response should be 422

  @error
  Scenario: Creating an artifact with too large of a name

    Given I have an artifact request
    And the name is over 8192 characters
    When I POST to "/api/artifacts"
    Then the response should be 422

  @error
  Scenario: Creating an artifact with too large of a version

    Given I have an artifact request
    And the version is over 255 characters
    When I POST to "/api/artifacts"
    Then the response should be 422

  @error
  Scenario: Creating an artifact with too large of a sourceUrl

    Given I have an artifact request
    And the sourceUrl is over 8192 characters
    When I POST to "/api/artifacts"
    Then the response should be 422
