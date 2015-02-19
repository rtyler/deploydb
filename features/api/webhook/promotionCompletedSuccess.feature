Feature: Webhook invocation when promotion is completed

  @wip @webhook
  Scenario: Webhooks should be invoked when promotion is completed
    Given the webhooks configuration:
    """
      promotion:
        completed: "http://{{webhook_url}}"
    """
    And there is an artifact
    And Given the service has one promotion criteria defined
    When I trigger deployment POST "/api/deployments/1/promotions" with:
    """
      {
          "name": "status-check"
          "url" : "http://local.lookout.com/jenkins/job-id/2/results"
          "status" : "SUCCESS"
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
        "status" : "VERIFIED",
        "promotion":[{
          "name": "status-check"
          "url" : "http://local.lookout.com/jenkins/job-id/2/results"
          "status" : "SUCCESS"
        }]
      }
    """
