Feature: Webhook invocation during CREATE API

  As administrator of the system, when webhooks are configured, then system should call
  appropriate webhook for CREATE API

  Scenario:  Creating an artifact with webhooks is configured

    Given I have an webhook service
    Given there is a service
    Given there is a environment
    When I PUT to "/api/v1/artifacts"
    Then the webhook should be recevied with:
    """
      {
        "artifactId" : 1,
        "deploymentId" : 1,
        "serviceName" : "bluffdale"
        "environmentName" : "dev-integ"
      }
    """

  @wip @webhook
  Scenario: Webhooks should be invoked when artifacts are created
    Given the webhooks configuration:
    """
    webhooks:
      deployment:
        start: "http://{{webhook_url}}"
    """
    And a service has been configured for the artifact
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
        "environment" : "pre-production",
        "status" : "STARTED",
        "createdAt" : "{{created_timestamp}}"
      }
    """
