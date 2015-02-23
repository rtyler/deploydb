Feature: Promotion READ APIs

  As a RESTful client or user
  I should be able to read information about promotions

  @wip
  Scenario: Fetching all promotions

    Given a promotion configuration name "basic":
    """
    type:  BasicPromotion
    description: "Smoke test the Fun as a Service service"
    """
    When I GET "/api/promotions"
    Then the response should be 200
    And the body should be JSON:
    """
      [{
        "ident" : "basic",
        "type" : "BasicPromotion",
        "description" : "Smoke test the Fun as a Service service"
      }]
    """

  @wip
  Scenario: Fetching an promotion by name that exists

    Given a promotion configuration name "basic":
    """
    type:  BasicPromotion
    description: "Smoke test the Fun as a Service service"
    """
    When I GET "/api/promotions/basic"
    Then the response should be 200
    And the body should be JSON:
    """
      {
        "ident" : "basic",
        "type" : "BasicPromotion",
        "description" : "Smoke test the Fun as a Service service"
      }
    """

  @wip
  Scenario: Fetching an promotion by name that doesn't exist

    When I GET "/api/promotions/faas"
    Then the response should be 404
