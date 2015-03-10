Feature: Promotion READ APIs

  As a RESTful client or user
  I should be able to read information about promotions


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


  Scenario: Fetching an promotion by name that doesn't exist

    When I GET "/api/promotions/faas"
    Then the response should be 404


  @freezetime @wip
  Scenario: Fetching a result for a Promotion associated with a Deployment

    Given there is a deployment
    When I GET "/api/deployments/1/promotions/1"
    Then the response should be 200
    And the body should be JSON:
    """
      {
        "id" : 1,
        "promotion" : "jenkins-smoke",
        "status" : "STARTED",
        "infoUrl" : null,
        "createdAt" : "{{created_timestamp}}"
      }
    """


  @wip
  Scenario: Fetching Promotion result for a Deployment that doesn't exist

    When I GET "/api/deployments/1/promotions/1"
    Then the response should be 404


  @error @wip
  Scenario: Fetching a result for a Promotion which is not associated with the Deployment

    Given there is a deployment
    When I GET "/api/deployments/1/promotions/5"
    Then the response should be 404
