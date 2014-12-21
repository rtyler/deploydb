Feature: The health and status endpoint

  Scenario: Fetching health under normal circumstances
    When I GET "/healthcheck" from the admin app
    Then the response should be 200
    And the response body should be:
    """
    {"deadlocks":{"healthy":true},"sanity":{"healthy":true}}
    """
