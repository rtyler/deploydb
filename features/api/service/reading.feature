Feature: Service READ APIs

  As a RESTful client or user
  I should be able to read information about services


  Scenario: Fetching all services 

    Given a service configuration named "faas":
    """
    description: "Fun as a Service"
    artifacts:
      - com.github.lookout:foas
      - com.github.lookout.puppet:puppet-foas
      - com.github.lookout:puppet-mysql
    pipelines:
      - devtoprod
    promotions:
      - status-check
      - jenkins-smoke
    """
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
            "devtoprod"
        ],
        "promotions" : [
            "status-check",
            "jenkins-smoke"
        ],
        "failure_strategy" : "Stop"
      }]
    """

  Scenario: Fetching an service by name that exists

    Given a service configuration named "faas":
    """
    description: "Fun as a Service"
    artifacts:
      - com.github.lookout:foas
      - com.github.lookout.puppet:puppet-foas
      - com.github.lookout:puppet-mysql
    pipelines:
      - devtoprod
    promotions:
      - status-check
      - jenkins-smoke
    """
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
            "devtoprod"
        ],
        "promotions" : [
            "status-check",
            "jenkins-smoke"
        ],
        "failure_strategy" : "Stop"
      }
    """


  Scenario: Fetching an service by name that doesn't exist

    When I GET "/api/services/faas"
    Then the response should be 404
