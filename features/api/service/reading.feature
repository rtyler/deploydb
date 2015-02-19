Feature: Service READ APIs

  As a RESTful client or user
  I should be able to read information about services


  Scenario: Fetching all services 

    Given there are services
    When I GET "/api/services"
    Then the response should be 200
    And the body should be JSON:
    """
      [{
        "ident" : "faas",
        "description" : "Fun as a Service",
        "artifacts" : [
            "com.github.lookout:foas",
            "com.github.lookout.puppet:puppet-foas",
            "com.github.lookout:puppet-mysql"
        ],
        "pipelines" : [
            "detoprod"
        ],
        "promotions" : [
            "status-check",
            "jenkins-smoke"
        ]
      },
      {
        "ident" : "alas",
        "description" : "Auditlog as a Service",
        "artifacts" : [
            "com.github.lookout:alas",
            "com.github.lookout.puppet:puppet-alas",
            "com.github.lookout:puppet-mysql"
        ],
        "pipelines" : [
            "detoprod"
        ],
        "promotions" : [
            "status-check",
            "jenkins-smoke"
        ]
      }]
    """

  Scenario: Fetching an service by name that exists

    Given there is an service
    When I GET "/api/services/faas"
    Then the response should be 200
    And the body should be JSON:
    """
      {
        "ident" : "faas",
        "description" : "Fun as a Service",
        "artifacts" : [
            "com.github.lookout:foas",
            "com.github.lookout.puppet:puppet-foas",
            "com.github.lookout:puppet-mysql"
        ],
        "pipelines" : [
            "detoprod"
        ],
        "promotions" : [
            "status-check",
            "jenkins-smoke"
        ]
      }
    """

  Scenario: Fetching an service by name that doesn't exist

    When I GET "/api/services/faas"
    Then the response should be 404
