Feature: Environment READ APIs

  As a RESTful client or user
  I should be able to read information about environments


  Scenario: Fetching all environments 

    Given there are environments
    When I GET "/api/environments"
    Then the response should be 200
    And the body should be JSON:
    """
      [{
        "ident" : "alas",
        "description" : "Auditlog as a Environment",
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
      },
      {
        "ident" : "faas",
        "description" : "Fun as a Environment",
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
      }]
    """

  Scenario: Fetching an environment by name that exists

    Given there is an environment
    When I GET "/api/environments/faas"
    Then the response should be 200
    And the body should be JSON:
    """
      {
        "ident" : "faas",
        "description" : "Fun as a Environment",
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

  Scenario: Fetching an environment by name that doesn't exist

    When I GET "/api/environments/faas"
    Then the response should be 404
