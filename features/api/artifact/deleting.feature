Feature: Artifact DELETE APIs

  As a RESTful client or user
  I should be *not* be able to delete artifacts


  Scenario: Attempting to delete an artifact

    Once artifacts are in DeployDB they're there forever. FOREVER.

    Given there is an artifact
    When I DELETE "/api/v1/artifacts/1"
    Then the response should be 405

