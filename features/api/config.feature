Feature: DeployDB config APIs

  As a DeployDB administrator
  I should be able to update DeployDB configuration

  @wip
  Scenario: When the system is idle, reloading the shall succeed

    Given configuration directory is "config"
    When I POST to "/tasks/configReload" from the admin app
    Then the response should be 204

  @wip
  Scenario: When system has deployments in progress, attempt to reload config should fail

    Given there is a deployment
    When I POST to "/tasks/configReload" from the admin app
    Then the response should be 400

