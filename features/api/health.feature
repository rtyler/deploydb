Feature: The health endpoint

  As a DeployDB administrator
  I should be able to view the health of the application

  Scenario: Fetching health under normal circumstances

    Assuming the application is up and running properly, the built-in
    healthcheck endpoint should return information about all the configured
    healthchecks currently running inside of DeployDB

    When I GET "/healthcheck" from the admin app
    Then the response should be 200
    And the response body should be:
    """
    {"deadlocks":{"healthy":true},"hibernate":{"healthy":true},"sanity":{"healthy":true}}
    """
