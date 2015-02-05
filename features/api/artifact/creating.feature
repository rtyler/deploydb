Feature: Artifact CREATE APIs

  As a RESTful client or user
  I should be able to create new artifacts in the system


  @freezetime
  Scenario: Creating a new artifact

    The creation of an artifact technically only requires group, name and
    version. However it's a good idea to always provide a `sourceUrl` as well
    in order future users of DeployDB to be able to backtrack an artifact all
    the way to the binary itself

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


  @error @wip
  Scenario: Creating an artifact without a version

    When I PUT to "/api/v1/artifacts" with:
    """
      {
        "group" : "com.example.cucumber",
        "name" : "cukes"
      }
    """
    Then the response should be 400

  @wip @error
  Scenario: Creating an artifact with too large of a group or name

    Given an artifact with a name over 8192 characters
    When I POST to "/api/v1/artifacts" with it
    Then the response should be 400
