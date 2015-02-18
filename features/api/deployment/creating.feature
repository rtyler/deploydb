Feature: Deployment CREATE APIs


  @freezetime
  Scenario: Creating a new deployment

    Creating a deployment is done automatically at the beginning of a flow for
    an Artifact. There's no need for an API client to create a deployment on
    their own.

    Given there is an artifact
    When I POST to "/api/deployments" with:
    """
      {
        "artifactId" : 1,
        "environment" : "pre-production"
      }
    """
    Then the response should be 405
