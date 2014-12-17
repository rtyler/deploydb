Feature: The health and status endpoint


  Scenario: Fetching health under normal circumstances
    When I GET "/health"
    Then I should receive a 200
    And the response body should include:
    """
    Everything is OK
    """

  Scenario: Fetching health.json under normal circumstances
    When I GET "/health"
    Then I should receive a 200
    And the response body should include:
    """
    Everything is OK
    """
