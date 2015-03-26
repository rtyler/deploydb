Feature: DeployDB UPDATE APIs

  As a RESTful client or user
  I should be able to update DeployDB configuration

  @wip
  Scenario: Reloading the config while system is idle will succeed

    Given configuration directory is "config"
    When I POST to "/configReload"
    Then the response should be 204

  @wip
  Scenario: When system has deployments in progress, attempt to reload config should fail

    Given there is a deployment
    When I POST to "/configReload"
    Then the response should be 400

