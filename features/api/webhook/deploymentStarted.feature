Feature: Webhook invocation  when deployment is started

  @freezetime @webhook
  Scenario: Webhooks should be invoked when deployment is started
    Given a deployment webhook "started" configuration:
    """
      deployment:
        started:
          - http://localhost:10000/job/notify-deployment-started/build
    """
    And there is a deployment in "CREATED" state
    When I PATCH "/api/deployments/1" with:
    """
      {
        "status" : "STARTED"
      }
    """
    Then the webhook should be invoked with the JSON:
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
        "status" : "STARTED",
        "service" : "faas",
        "environment" : "pre-prod",
        "createdAt" : "{{created_timestamp}}"
      }
    """

  @freezetime @webhook
  Scenario: Environment webhooks should be invoked when artifacts are started
    Given an deployment environment webhook "started" configuration named "pre-prod":
    """
    description: "DeployDB Primary Integration"
    webhooks:
      deployment:
        started:
          - http://localhost:10000/job/notify-deployment-started/build
    """
    And there is a deployment in "CREATED" state
    When I PATCH "/api/deployments/1" with:
    """
      {
        "status" : "STARTED"
      }
    """
    Then the webhook should be invoked with the JSON:
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
        "status" : "STARTED",
        "service" : "faas",
        "environment" : "pre-prod",
        "createdAt" : "{{created_timestamp}}"
      }
    """