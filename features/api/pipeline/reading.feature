Feature: Pipeline READ APIs
  As a RESTful client or user
  I should be able to read information about pipelines


  Scenario: Fetching all pipelines

    Given a pipeline configuration named "devtoprod":
    """
    description: "Development to production pipeline"
    environments:
         dev-alpha:
         dev-beta:
         integ:
         preprod:
           promotions:
              - prod-preflight
              - manual
         prod:
    """
    When I GET "/api/pipelines"
    Then the response should be 200
    And the body should be JSON:
    """
      [{
          "ident" : "devtoprod",
          "description": "Development to production pipeline",
          "environments": {
            "dev-alpha": {
              "promotions": []
            },
            "dev-beta": {
              "promotions": []
            },
            "integ": {
              "promotions": []
            },
            "preprod": {
              "promotions": [
                "prod-preflight",
                "manual"
              ]
            },
            "prod": {
              "promotions": []
            }
          }
      }]
    """


    Scenario: Fetching an pipeline by name that exists

      Given a pipeline configuration named "devtoprod":
      """
    description: "Development to production pipeline"
    environments:
        dev-alpha:
        dev-beta:
        integ:
        pre-prod:
          promotions:
            - prod-preflight
            - manual
        prod:
      """
      When I GET "/api/pipelines/devtoprod"
      Then the response should be 200
      And the body should be JSON:
      """
      {
        "ident" : "devtoprod",
        "description": "Development to production pipeline",
        "environments": {
          "dev-alpha": {
            "promotions": []
          },
          "dev-beta": {
            "promotions": []
          },
          "integ": {
            "promotions": []
          },
          "pre-prod": {
            "promotions": [
              "prod-preflight",
              "manual"
            ]
          },
          "prod": {
            "promotions": []
          }
        }
      }
      """


      Scenario: Fetching an pipeline by name that doesn't exist

        When I GET "/api/pipelines/fass"
        Then the response should be 404