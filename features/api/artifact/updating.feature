Feature: Artifact UPDATE APIs

  As a RESTful client or user
  I should be *not* be able to update artifacts


  Scenario: Attempting to update an artifact

    Artifacts must be considered immutable to ensure consistency of data from
    DeployDB. We can't different binaries with the same version, that's silly.

    Given there is an artifact
    When I PATCH "/api/artifacts/1" with:
    """
      {
        "version" : "9.0.0.0"
      }
    """
    Then the response should be 405
