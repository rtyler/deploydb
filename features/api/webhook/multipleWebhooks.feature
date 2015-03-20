Feature: Webhook invocation  when deployment is created with multiple webhooks

  @freezetime @webhook
  Scenario: Multiple Webhooks should be invoked when artifacts are created
    Given a webhook "created" configuration:
    """
      deployment:
        created:
           - http://localhost:10000/job/notify-deployment-started/build
           - http://localhost:10000/job/notify-deployment-started_another/build
    """
    And promotions are configured
    And an environment is configured
    And a pipeline is configured
    And a service is configured
    When I POST to "/api/artifacts" with an artifact
    Then the webhook 1 should be invoked with the JSON:
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
    And the webhook 2 should be invoked with the JSON:
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

  @freezetime @webhook
  Scenario: Multiple environment webhooks should be invoked when artifacts are created
    Given an environment webhook "created" configuration named "integ":
    """
    description: "DeployDB Primary Integration"
    webhooks:
      deployment:
        created:
          - http://localhost:10000/job/notify-deployment-started/build
          - http://localhost:10000/job/notify-deployment-started_another/build
    """
    And promotions are configured
    And a pipeline is configured
    And a service is configured
    When I POST to "/api/artifacts" with an artifact
    Then the webhook 1 should be invoked with the JSON:
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
    And the webhook 2 should be invoked with the JSON:
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

  @freezetime @webhook
  Scenario: Global webhook and environment webhook should be invoked when artifacts are created
    Given a webhook "created" configuration:
    """
      deployment:
        created:
           - http://localhost:10000/job/notify-deployment-started/build
    """
    And an environment webhook "created" configuration named "integ":
    """
    description: "DeployDB Primary Integration"
    webhooks:
      deployment:
        created:
          - http://localhost:10000/job/notify-deployment-started_another/build
    """
    And promotions are configured
    And a pipeline is configured
    And a service is configured
    When I POST to "/api/artifacts" with an artifact
    Then the webhook 1 should be invoked with the JSON:
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
    And the webhook 2 should be invoked with the JSON:
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
