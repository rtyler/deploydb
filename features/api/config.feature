Feature: DeployDB config APIs

  As a DeployDB administrator
  I should be able to update DeployDB configuration


  Scenario: When the system is idle, reloading the shall succeed

    Given DeployDb configuration directory path is "./config"
    When I POST to "/tasks/configReload" from the admin app
    Then the response should be 200
    And the response body should be:
    """
    Done!
    """


  Scenario: If deployments are NOT verified i.e. are in progress, then attempt to reload
            config should fail. A deployment is considered as in progress, if the status
            is NOT SUCCESS or FAILED

    Given there is a deployment
    When I POST to "/tasks/configReload" from the admin app
    Then the response should be 200
    And the response body should be:
    """
    Failed: Configuration reload is not allowed while deployments are in progress
    """

