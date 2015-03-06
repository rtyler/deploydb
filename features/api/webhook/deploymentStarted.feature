Feature: Webhook invocation  when deployment is started

  @wip @feezetime @webhook
  Scenario: Webhooks should be invoked when deployment is started
    Given the webhooks configuration:
    """
      deployment:
        started: "http://{{webhook_url}}"
    """
    And there is a deployment
    When I trigger deployment PATCH "/api/deployments/1" with:
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
        "service" : "fun as service"
        "environment" : "dev-apha",
        "status" : "STARTED",
      }
    """