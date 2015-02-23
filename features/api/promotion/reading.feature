Feature: Promotion READ APIs

  As a RESTful client or user
  I should be able to read information about promotions


  @wip
  Scenario: Fetching all promotions

    Given a promotion configuration name "jenkins":
    """
    type:  JenkinsPromotion
    jobs:
       - basic-smokes-test
       - basic-perf-test
       - end2end-smoke-test
    """
    Given a promotion configuration named "prod":
    """
    type:  ProductionPromotion
    jobs:
       - prod-smokes-test
       - prod-perf-tests
       - prod-santiy-tests
    """
    When I GET "/api/promotions"
    Then the response should be 200
    And the body should be JSON:
    """
      [{
        "ident" : "jenkins",
        "type" : "JenkinsPromotion",
        "jobs" : [
            "basic-smokes-test",
            "basic-perf-test",
            "end2end-smoke-test"
        ]
      },
      {
        "ident" : "prod",
        "type" : "ProductionPromotion",
        "jobs" : [
            "prod-smokes-test",
            "prod-perf-tests",
            "prod-sanity-tests"
        ]
      }]
    """


  @wip
  Scenario: Fetching an promotion by name that exists

    Given a promotion configuration name "jenkins":
    """
    type:  JenkinsPromotion
    jobs:
       - basic-smokes-test
       - basic-perf-test
       - end2end-smoke-test
    """
    Given a promotion configuration named "prod":
    """
    type:  ProductionPromotion
    jobs:
       - prod-smokes-test
       - prod-perf-tests
       - prod-santiy-tests
    """
    When I GET "/api/promotions/jenkins"
    Then the response should be 200
    And the body should be JSON:
    """
      {
        "ident" : "jenkins",
        "type" : "JenkinsPromotion",
        "jobs" : [
            "basic-smokes-test",
            "basic-perf-test",
            "end2end-smoke-test"
        ]
      }
    """

  @wip
  Scenario: Fetching an promotion by name that doesn't exist

    When I GET "/api/Promotions/faas"
    Then the response should be 404
