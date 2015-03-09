Feature: Webhook invocation  when deployment is completed

  @freezetime @webhook
  Scenario: Webhooks should be invoked when deployment is completed
    Given a webhook "completed" configuration:
    """
      deployment:
        completed:
          - http://localhost:10000/job/notify-deployment-started/build
    """
    And there is a deployment in "STARTED" state
    When I PATCH "/api/deployments/1" with:
    """
      {
        "status" : "COMPLETED"
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
        "status" : "COMPLETED",
        "service" : "faas",
        "environment" : "pre-prod",
        "createdAt" : "{{created_timestamp}}"
      }
    """