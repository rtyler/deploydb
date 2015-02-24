Feature: Environment READ APIs

  As a RESTful client or user
  I should be able to read information about environments


  @wip
  Scenario: Fetching all environments

    Given an environment configuration named "integ":
    """
    description: "DeployDB Primary Integration"
    webhooks:
      deployment:
        created:
          - http://jenkins.example.com/job/integ-deploy-created/build
        completed:
          - http://jenkins.example.com/job/integ-deploy-completed/build
    """
    When I GET "/api/environments"
    Then the response should be 200
    And the body should be JSON:
    """
      [{
        "ident" : "integ",
        "description" : "DeployDB Primary Integration",
        "webhooks" : {
          "deployment" : {
              "started" : null,
              "created" : ["http://jenkins.example.com/job/integ-deploy-created/build"],
              "completed" : ["http://jenkins.example.com/job/integ-deploy-completed/build"],
              "verified" : null
          },
          "promotion" : null
        }
      }]
    """


  @wip
  Scenario: Fetching an environment by name that exists

    Given an environment configuration named "integ":
    """
    description: "DeployDB Primary Integration"
    webhooks:
      deployment:
        created:
          - http://jenkins.example.com/job/integ-deploy-created/build
        completed:
          - http://jenkins.example.com/job/integ-deploy-completed/build
    """
    When I GET "/api/environments/integ"
    Then the response should be 200
    And the body should be JSON:
    """
      {
        "ident" : "integ",
        "description" : "DeployDB Primary Integration",
        "webhooks" : {
          "deployment" : {
              "started" : null,
              "created" : ["http://jenkins.example.com/job/integ-deploy-created/build"],
              "completed" : ["http://jenkins.example.com/job/integ-deploy-completed/build"],
              "verified" : null
          },
          "promotion" : null
        }
      }
    """


  @wip @error
  Scenario: Fetching an environment by name that doesn't exist

    When I GET "/api/environments/faas"
    Then the response should be 404
