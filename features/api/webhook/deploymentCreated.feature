Feature: Webhook invocation  when deployment is created

  @wip @webhook
  Scenario: Webhooks should be invoked when artifacts are created
    Given the webhooks configuration:
    """
      deployment:
        created: "http://{{webhook_url}}"
    """
    And Given a service is configured
    When I POST to "/api/v1/artifacts" with an artifact
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
        "status" : "NOTSTARTED",
        "createdAt" : "{{created_timestamp}}"
      }
    """
