Feature: Deployment READ APIs

  As a RESTful client or user
  I should be able to read information about deployments


  @freezetime
  Scenario: Fetching all deployments

    Given there is a deployment
    When I GET "/api/deployments"
    Then the response should be 200
    And the body should be JSON:
    """
      [{
        "id" : 1,
        "artifact" : {
          "id" : 1,
          "group" : "com.example.cucumber",
          "name" : "cucumber-artifact",
          "version" : "1.0.1",
          "sourceUrl" : "http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.1/cucumber-artifact-1.0.1.jar",
          "createdAt" : "{{created_timestamp}}"
        },
        "environment" : "pre-prod",
        "service" : "faas",
        "status" : "STARTED",
        "promotionResultSet" : [{
          "id" : 1,
          "promotion" : "jenkins-smoke",
          "status" : "IN_PROGRESS",
          "infoUrl" : "",
          "createdAt" : "{{created_timestamp}}"
         }],
        "createdAt" : "{{created_timestamp}}"
      }]
    """


  @freezetime
  Scenario: Fetching a deployment by ID

    Given there is a deployment
    When I GET "/api/deployments/1"
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
        "environment" : "pre-prod",
        "service" : "faas",
        "status" : "STARTED",
        "promotionResultSet" : [{
          "id" : 1,
          "promotion" : "jenkins-smoke",
          "status" : "IN_PROGRESS",
          "infoUrl" : "",
          "createdAt" : "{{created_timestamp}}"
         }],
        "createdAt" : "{{created_timestamp}}"
      }
    """


  Scenario: Fetching a deployment by ID that doesn't exist

    When I GET "/api/deployments/1"
    Then the response should be 404


  @error
  Scenario: Fetching an deployment by a non-numeric ID

    When I GET "/api/deployments/0xdeadbeef"
    Then the response should be 400


  @wip
  Scenario: Fetching an deployment with the proper content type

  It is encouraged to specify the version of the API you would like to
  receive inside of a Accept header. If this header is not specified than the
  client is expected to receive the v1 version of the JSON API

    Given there is a deployment
    When I GET "/api/deployments/1" with custom headers:
      | Header Name | Value                            |
      | Accept      | application/vnd.deploydb.v1+json |
    Then the response should be 200


  @error
  Scenario: Fetching an deployment with an invalid content type

  It is encouraged to specify the version of the API you would like to
  receive inside of a Accept header. If this header is not specified than the
  client is expected to receive the v1 version of the JSON API

    Given there is a deployment
    When I GET "/api/deployments/1" with custom headers:
      | Header Name | Value           |
      | Accept      | application/xml |
    Then the response should be 406


  @freezetime
  Scenario: Fetching an deployment by the pageNumber and perPageSize

    Given there are deployments
    When I GET "/api/deployments?pageNumber=0&perPageSize=5"
    Then the response should be 200
    And the body should be JSON:
    """
       [{
        "id" : 1,
        "artifact" : {
          "id" : 1,
          "group" : "com.example.cucumber",
          "name" : "cucumber-artifact",
          "version" : "1.0.1",
          "sourceUrl" : "http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.1/cucumber-artifact-1.0.1.jar",
          "createdAt" : "{{created_timestamp}}"
        },
        "environment" : "pre-prod",
        "service" : "faas",
        "status" : "STARTED",
        "promotionResultSet" : [{
          "id" : 1,
          "promotion" : "jenkins-smoke",
          "status" : "IN_PROGRESS",
          "infoUrl" : "",
          "createdAt" : "{{created_timestamp}}"
        }],
        "createdAt" : "{{created_timestamp}}"
      },
      {
        "id" : 2,
        "artifact" : {
          "id" : 2,
          "group" : "com.example.cucumber",
          "name" : "cucumber-artifact",
          "version" : "1.0.2",
          "sourceUrl" : "http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.2/cucumber-artifact-1.0.2.jar",
          "createdAt" : "{{created_timestamp}}"
        },
        "environment" : "pre-prod",
        "service" : "faas",
        "status" : "STARTED",
        "promotionResultSet" : [{
          "id" : 2,
          "promotion" : "status-check",
          "status" : "IN_PROGRESS",
          "infoUrl" : "http://local.lookout.com/jenkins/job-id/2/results",
          "createdAt" : "{{created_timestamp}}"
        }],
        "createdAt" : "{{created_timestamp}}"
      }]
      """


  @error
  Scenario: Fetching an deployment with invalid page number

    When I GET "/api/deployments?pageNumber=1&perPageSize=5"
    Then the response should be 404


  @error
  Scenario: Fetching an deployment with invalid data type for pageSize

    When I GET "/api/deployments?pageNumber=-1&perPageSize=0xdeadbeef"
    Then the response should be 400


  @freezetime
  Scenario: Fetching the latest deployment

    Given there is a deployment
    When I GET "/api/deployments/latest"
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
        "environment" : "pre-prod",
        "service" : "faas",
        "status" : "STARTED",
        "promotionResultSet" : [{
          "id" : 1,
          "promotion" : "jenkins-smoke",
          "status" : "IN_PROGRESS",
          "infoUrl" : "",
          "createdAt" : "{{created_timestamp}}"
        }],
        "createdAt" : "{{created_timestamp}}"
      }
    """

  @freezetime
  Scenario: Fetching the latest deployment when multiple deployments are present

    Given there are deployments
    When I GET "/api/deployments/latest"
    Then the response should be 200
    And the body should be JSON:
    """
      {
        "id" : 2,
        "artifact" : {
          "id" : 2,
          "group" : "com.example.cucumber",
          "name" : "cucumber-artifact",
          "version" : "1.0.2",
          "sourceUrl" : "http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.2/cucumber-artifact-1.0.2.jar",
          "createdAt" : "{{created_timestamp}}"
        },
        "environment" : "pre-prod",
        "service" : "faas",
        "status" : "STARTED",
        "promotionResultSet" : [{
          "id" : 2,
          "promotion" : "status-check",
          "status" : "IN_PROGRESS",
          "infoUrl" : "http://local.lookout.com/jenkins/job-id/2/results",
          "createdAt" : "{{created_timestamp}}"
        }],
        "createdAt" : "{{created_timestamp}}"
      }
    """

  @freezetime
  Scenario: Fetching an deployment by pageNumber=0 and perPageSize=0 returns 0 deployments

    Given there are deployments
    When I GET "/api/deployments?pageNumber=0&perPageSize=0"
    Then the response should be 200
