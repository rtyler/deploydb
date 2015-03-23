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


  @freezetime @webhook
  Scenario: Both global and environment webhooks should be invoked when promotion is completed with success
    Given a webhook "completed" configuration:
    """
      promotion:
        completed:
          - http://localhost:10000/job/notify-promotion-completed/build
    """
    And an environment webhook "completed" configuration named "pre-prod":
    """
    description: "DeployDB Primary Integration"
    webhooks:
      promotion:
        completed:
          - http://localhost:10000/job/notify-promotion-completed_another/build
    """
    And there is a deployment in "COMPLETED" state
    When I POST to "/api/deployments/1/promotions" with:
    """
      {
        "name"  : "jenkins-smoke",
        "status" : "SUCCESS",
        "infoUrl" : "http://local.lookout.com/jenkins/job-id/2/results"
      }
    """
    Then the webhook 1 should be invoked with the JSON:
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
          "status" : "SUCCESS"
        }
      }
    """
    And the webhook 2 should be invoked with the JSON:
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
          "status" : "SUCCESS"
        }
      }
    """

  @freezetime @webhook
  Scenario: Only Global webhook should be invoked when environment webhooks is missing created event
    Given a webhook "created" configuration:
    """
      deployment:
        created:
          - http://localhost:10000/notify-deployment-created/build
        started:
          - http://localhost:10000/job/notify-deployment-started/build
    """
    And an environment webhook "created" configuration named "integ":
    """
    description: "DeployDB Primary Integration"
    webhooks:
      deployment:
        completed:
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

  @freezetime @webhook
  Scenario: Only environment webhooks should be invoked when global webhooks is missing created event
    Given a webhook "created" configuration:
    """
      deployment:
        started:
          - http://localhost:10000/job/notify-deployment-started/build
        completed:
          - http://localhost:10000/job/notify-deployment-started_another/build
    """
    And an environment webhook "created" configuration named "integ":
    """
    description: "DeployDB Primary Integration"
    webhooks:
      deployment:
        created:
          - http://localhost:10000/notify-deployment-created/build
          - http://localhost:10000/notify-deployment-created_another/build
        completed:
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
  Scenario: Only global webhooks should be invoked when environment webhooks is missing started event
    Given a webhook "started" configuration:
    """
      deployment:
        created:
          - http://localhost:10000/job/notify-deployment-created/build
        started:
          - http://localhost:10000/job/notify-deployment-started/build
          - http://localhost:10000/job/notify-deployment-started_another/build
        completed:
          - http://localhost:10000/job/notify-deployment-completed/build
          - http://localhost:10000/job/notify-deployment-completed_another/build
      promotion:
        completed:
          - http://localhost:10000/job/notify-completed-completed/build
    """
    And an environment webhook "started" configuration named "pre-prod":
    """
    description: "DeployDB Primary Integration"
    webhooks:
      deployment:
        created:
          - http://localhost:10000/notify-deployment-created/build
        completed:
          - http://localhost:10000/job/notify-deployment-completed/build
    """
    And there is a deployment in "CREATED" state
    When I PATCH "/api/deployments/1" with:
    """
      {
        "status" : "STARTED"
      }
    """
    Then the webhook 1 should be invoked with the JSON:
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
    And the webhook 2 should be invoked with the JSON:
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
  Scenario: Global webhooks should be invoked when environment webhook is missing for promotion success
    Given a webhook "completed" configuration:
    """
      promotion:
        completed:
          - http://localhost:10000/job/notify-promotion-completed/build
          - http://localhost:10000/job/notify-promotion-completed_another/build
    """
    And an environment webhook "completed" configuration named "pre-prod":
    """
    description: "DeployDB Primary Integration"
    """
    And there is a deployment in "COMPLETED" state
    When I POST to "/api/deployments/1/promotions" with:
    """
      {
        "name"  : "jenkins-smoke",
        "status" : "SUCCESS",
        "infoUrl" : "http://local.lookout.com/jenkins/job-id/2/results"
      }
    """
    Then the webhook 1 should be invoked with the JSON:
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
          "status" : "SUCCESS"
        }
      }
    """
    And the webhook 2 should be invoked with the JSON:
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
          "status" : "SUCCESS"
        }
      }
    """


  @freezetime @webhook
  Scenario: Environment webhooks should be invoked when global webhook is missing for promotion success
    Given a webhook "completed" configuration:
    """
      deployment:
        completed:
          - http://localhost:10000/job/notify-deployment-completed/build
          - http://localhost:10000/job/notify-deployment-completed_another/build
    """
    And an environment webhook "completed" configuration named "pre-prod":
    """
    description: "DeployDB Primary Integration"
    webhooks:
      promotion:
        completed:
          - http://localhost:10000/job/notify-promotion-completed/build
          - http://localhost:10000/job/notify-promotion-completed_another/build

    """
    And there is a deployment in "COMPLETED" state
    When I POST to "/api/deployments/1/promotions" with:
    """
      {
        "name"  : "jenkins-smoke",
        "status" : "SUCCESS",
        "infoUrl" : "http://local.lookout.com/jenkins/job-id/2/results"
      }
    """
    Then the webhook 1 should be invoked with the JSON:
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
          "status" : "SUCCESS"
        }
      }
    """
    And the webhook 2 should be invoked with the JSON:
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
          "status" : "SUCCESS"
        }
      }
    """
