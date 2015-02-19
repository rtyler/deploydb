Feature: Webhook invocation  when deployment is created

  @wip @webhook
  Scenario: Webhooks should be invoked when artifacts are created
    Given the webhooks configuration:
    """
      deployment:
        created: "http://{{webhook_url}}"
    """
    And the service configuration:
    """
    name: "Fun as a Service"
    artifacts:
      - com.github.lookout:foas
      - com.github.lookout.puppet:puppet-foas
      - com.github.lookout:puppet-mysql
      - com.example.cucumber:cucumber-artifact
    pipelines:
      - devtoprod
    promotions:
      - status-check
      - jenkins-smoke
    """
    When I PUT to "/api/v1/artifacts" with an artifact
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
