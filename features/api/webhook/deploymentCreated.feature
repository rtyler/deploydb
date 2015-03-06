Feature: Webhook invocation  when deployment is created

  @freezetime
  Scenario: Webhooks should be invoked when artifacts are created
    Given the webhooks configuration:
    """
      deployment:
        created:
           - http://localhost:10000/job/notify-deployment-started/build
    """
    And promotions are configured
    And an environment is configured
    And a pipeline is configured
    And a service is configured
    When I POST to "/api/artifacts" with an artifact
    And Wait for 1 seconds
    Then the webhook should be invoked with the JSON:
    """
      {
        "id" : 1,
        "artifact" : {
          "id" : 1,
          "group" : "com.example.cucumber",
          "name" : "cukes",
          "version" : "1.0.1",
          "sourceUrl" : "http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.1/cucumber-artifact-1.0.1.jar",
          "createdAt" : "{{created_timestamp}}"
        },
        "status" : "CREATED",
        "service" : "faas",
        "environment" : "integ",
        "createdAt" : "{{created_timestamp}}"
      }
    """

  @freezetime
  Scenario: Environment webhooks should be invoked when artifacts are created
    Given an environment webhook configuration named "integ":
    """
    description: "DeployDB Primary Integration"
    webhooks:
      deployment:
        created:
          - http://localhost:10000/job/notify-deployment-started/build
    """
    And promotions are configured
    And a pipeline is configured
    And a service is configured
    When I POST to "/api/artifacts" with an artifact
    And Wait for 1 seconds
    Then the webhook should be invoked with the JSON:
    """
      {
        "id" : 1,
        "artifact" : {
          "id" : 1,
          "group" : "com.example.cucumber",
          "name" : "cukes",
          "version" : "1.0.1",
          "sourceUrl" : "http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.1/cucumber-artifact-1.0.1.jar",
          "createdAt" : "{{created_timestamp}}"
        },
        "status" : "CREATED",
        "service" : "faas",
        "environment" : "integ",
        "createdAt" : "{{created_timestamp}}"
      }
    """
