Feature: Webhook invocation when promotion is completed with failed state

  @freezetime @webhook
  Scenario: Webhooks should be invoked when promotion is completed in failed state
    Given a webhook "completed" configuration:
    """
      promotion:
        completed:
          - http://localhost:10000/job/notify-promotion-completed/build
    """
    And there is a deployment in "COMPLETED" state
    When I POST to "/api/deployments/1/promotions" with:
    """
      {
        "name"  : "jenkins-smoke",
        "status" : "FAILED",
        "infoUrl" : "http://local.lookout.com/jenkins/job-id/2/results"
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
        "status" : "VERIFIED",
        "service" : "faas",
        "environment" : "pre-prod",
        "createdAt" : "{{created_timestamp}}",
        "promotionResult":{
          "id" : 1,
          "createdAt" : "{{created_timestamp}}",
          "promotion": "jenkins-smoke",
          "infoUrl" : "http://local.lookout.com/jenkins/job-id/2/results",
          "status" : "FAILED"
        }
      }
    """

  @freezetime @webhook
  Scenario: Environment webhooks should be invoked when promotions are completed in failed state
    Given an environment webhook "completed" configuration named "pre-prod":
    """
    description: "DeployDB Primary Integration"
    webhooks:
      promotion:
        completed:
          - http://localhost:10000/job/notify-promotion-completed/build
    """
    And there is a deployment in "COMPLETED" state
    When I POST to "/api/deployments/1/promotions" with:
    """
      {
        "name"  : "jenkins-smoke",
        "status" : "FAILED",
        "infoUrl" : "http://local.lookout.com/jenkins/job-id/2/results"
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
        "status" : "VERIFIED",
        "service" : "faas",
        "environment" : "pre-prod",
        "createdAt" : "{{created_timestamp}}",
        "promotionResult":{
          "id" : 1,
          "createdAt" : "{{created_timestamp}}",
          "promotion": "jenkins-smoke",
          "infoUrl" : "http://local.lookout.com/jenkins/job-id/2/results",
          "status" : "FAILED"
        }
      }
    """
