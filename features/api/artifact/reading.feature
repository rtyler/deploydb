Feature: Artifact READ APIs

  As a RESTful client or user
  I should be able to read information about artifacts


  @freezetime
  Scenario: Fetching an artifact by ID that exists

    Given there is an artifact
    When I GET "/api/artifacts/1"
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

  @wip
  Scenario: Fetching an artifact with the proper content type

    It is encouraged to specify the version of the API you would like to
    receive inside of a Accept header. If this header is not specified than the
    client is expected to receive the v1 version of the JSON API

    Given there is an artifact
    When I GET "/api/artifacts/1" with custom headers:
      | Header Name | Value                            |
      | Accept      | application/vnd.deploydb.v1+json |
    Then the response should be 200

  @error
  Scenario: Fetching an artifact with an invalid content type

    It is encouraged to specify the version of the API you would like to
    receive inside of a Accept header. If this header is not specified than the
    client is expected to receive the v1 version of the JSON API

    Given there is an artifact
    When I GET "/api/artifacts/1" with custom headers:
      | Header Name | Value           |
      | Accept      | application/xml |
    Then the response should be 406

  Scenario: Fetching an artifact by ID that doesn't exist

    When I GET "/api/artifacts/1"
    Then the response should be 404

  @error
  Scenario: Fetching an artifact by a non-numeric ID

    When I GET "/api/artifacts/0xdeadbeef"
    Then the response should be 400


  @freezetime
  Scenario: Fetching an artifact by the (group, name) tuple

    Given there is an artifact
    When I GET "/api/artifacts/by-module/com.example.cucumber:cucumber-artifact"
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

      Given there are artifacts
      When I GET "/api/artifacts/by-module/com.example.cucumber:cucumber-artifact?pageNumber=0&perPageSize=5"
      Then the response should be 200
      And the body should be JSON:
      """
        [{
          "id" : 2,
          "createdAt" : "{{created_timestamp}}",
          "group" : "com.example.cucumber",
          "name" : "cucumber-artifact",
          "version" : "1.0.2",
          "sourceUrl" : "http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.2/cucumber-artifact-1.0.2.jar"
        },
        {
          "id" : 1,
          "createdAt" : "{{created_timestamp}}",
          "group" : "com.example.cucumber",
          "name" : "cucumber-artifact",
          "version" : "1.0.1",
          "sourceUrl" : "http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.1/cucumber-artifact-1.0.1.jar"
        }]
      """
  @error
  Scenario: Fetching an artifact by the (group, name) tuple that doesn't exist

    When I GET "/api/artifacts/by-module/com.example.cucumber:cucumber-artifact"
    Then the response should be 404
  
  @error
  Scenario: Fetching an artifact by the (group, name) tuple with invalid page number

    When I GET "/api/artifacts/by-module/com.example.cucumber:cucumber-artifact?pageNumber=1&perPageSize=5"
    Then the response should be 404

  @error
  Scenario: Fetching an artifact by the (group, name) tuple with invalid data type for pageSize

    When I GET "/api/artifacts/by-module/com.example.cucumber:cucumber-artifact?pageNumber=-1&perPageSize=0xdeadbeef"
    Then the response should be 400

  @error
  Scenario: Fetching an artifact by the (group, name) tuple, pageNumber=0 and perPageSize=0

    Given there are artifacts
    When I GET "/api/artifacts/by-module/com.example.cucumber:cucumber-artifact?pageNumber=0&perPageSize=0"
    Then the response should be 400


  @freezetime
  Scenario: Fetching the latest artifact by the (group, name) tuple

    Given there is an artifact
    When I GET "/api/artifacts/by-module/com.example.cucumber:cucumber-artifact/latest"
    Then the response should be 200
    And the body should be JSON:
    """
     {
        "id" : 1,
        "createdAt" : "{{created_timestamp}}",
        "group" : "com.example.cucumber",
        "name" : "cucumber-artifact",
        "version" : "1.0.1",
        "sourceUrl" : "http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.1/cucumber-artifact-1.0.1.jar"
      } 
    """

  @freezetime
  Scenario: Fetching the latest artifact by the (group, name) tuple when multiple artifacts are present

    Given there are artifacts
    When I GET "/api/artifacts/by-module/com.example.cucumber:cucumber-artifact/latest"
    Then the response should be 200
    And the body should be JSON:
    """
     {
        "id" : 2,
        "createdAt" : "{{created_timestamp}}",
        "group" : "com.example.cucumber",
        "name" : "cucumber-artifact",
        "version" : "1.0.2",
        "sourceUrl" : "http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.2/cucumber-artifact-1.0.2.jar"
      }
    """

  @wip
  Scenario: Fetching an artifact's versions

    Given there is an artifact
    When I GET "/api/artifacts/1/versions"
    Then the response should be 200

  @wip
  Scenario: Fetching the deployments for an artifact

    Given there is a deployment
    When I GET "/api/artifacts/1/deployments"
    Then the response should be 200
