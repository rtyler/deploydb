$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("api/artifact.feature");
formatter.feature({
  "line": 1,
  "name": "Artifact APIs",
  "description": "\nAs a RESTful client or user\nI should be able to read information about artifacts",
  "id": "artifact-apis",
  "keyword": "Feature"
});
formatter.before({
  "duration": 2058308757,
  "status": "passed"
});
formatter.scenario({
  "line": 7,
  "name": "Fetching an artifact by ID that exists",
  "description": "",
  "id": "artifact-apis;fetching-an-artifact-by-id-that-exists",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 9,
  "name": "there is an artifact with ID 1",
  "keyword": "Given "
});
formatter.step({
  "line": 10,
  "name": "I GET \"/api/v1/artifacts/1\"",
  "keyword": "When "
});
formatter.step({
  "line": 11,
  "name": "the response should be 200",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "1",
      "offset": 29
    }
  ],
  "location": "ArtifactSteps.groovy:8"
});
formatter.result({
  "duration": 129468109,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/artifacts/1",
      "offset": 7
    }
  ],
  "location": "HttpSteps.groovy:31"
});
formatter.result({
  "duration": 170942157,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:35"
});
formatter.result({
  "duration": 10635546,
  "status": "passed"
});
formatter.after({
  "duration": 17785204,
  "status": "passed"
});
formatter.before({
  "duration": 328865102,
  "status": "passed"
});
formatter.scenario({
  "line": 14,
  "name": "Fetching an artifact by ID that doesn\u0027t exist",
  "description": "",
  "id": "artifact-apis;fetching-an-artifact-by-id-that-doesn\u0027t-exist",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 13,
      "name": "@wip"
    }
  ]
});
formatter.step({
  "line": 16,
  "name": "I GET \"/api/v1/artifacts/1\"",
  "keyword": "When "
});
formatter.step({
  "line": 17,
  "name": "the response should be 404",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/artifacts/1",
      "offset": 7
    }
  ],
  "location": "HttpSteps.groovy:31"
});
formatter.result({
  "duration": 4497224,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "404",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:35"
});
formatter.result({
  "duration": 4820798,
  "error_message": "Assertion failed: \n\nassert response.status \u003d\u003d statusCode\n       |        |      |  |\n       |        200    |  404\n       |               false\n       GET http://localhost:8080/api/v1/artifacts/1 returned a response status of 200 OK\n\n\tat HttpSteps$_run_closure5.doCall(HttpSteps.groovy:36)\n\tat ✽.Then the response should be 404(api/artifact.feature:17)\n",
  "status": "failed"
});
formatter.after({
  "duration": 8750606,
  "status": "passed"
});
formatter.before({
  "duration": 323509406,
  "status": "passed"
});
formatter.scenario({
  "line": 20,
  "name": "Fetching an artifact by the name that exists",
  "description": "",
  "id": "artifact-apis;fetching-an-artifact-by-the-name-that-exists",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 22,
  "name": "there is an artifact named \"cucumber-artifact\"",
  "keyword": "Given "
});
formatter.step({
  "line": 23,
  "name": "I GET \"/api/v1/artifacts/named/cucumber-artifact\"",
  "keyword": "When "
});
formatter.step({
  "line": 24,
  "name": "the response should be 200",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "cucumber-artifact",
      "offset": 28
    }
  ],
  "location": "ArtifactSteps.groovy:12"
});
formatter.result({
  "duration": 117117,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/artifacts/named/cucumber-artifact",
      "offset": 7
    }
  ],
  "location": "HttpSteps.groovy:31"
});
formatter.result({
  "duration": 10053201,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:35"
});
formatter.result({
  "duration": 187528,
  "status": "passed"
});
formatter.after({
  "duration": 16281965,
  "status": "passed"
});
formatter.before({
  "duration": 313860489,
  "status": "passed"
});
formatter.scenario({
  "line": 27,
  "name": "Fetching an artifact by the name that doesn\u0027t exist",
  "description": "",
  "id": "artifact-apis;fetching-an-artifact-by-the-name-that-doesn\u0027t-exist",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 26,
      "name": "@wip"
    }
  ]
});
formatter.step({
  "line": 29,
  "name": "I GET \"/api/v1/artifacts/named/cucumber-artifact\"",
  "keyword": "When "
});
formatter.step({
  "line": 30,
  "name": "the response should be 404",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/artifacts/named/cucumber-artifact",
      "offset": 7
    }
  ],
  "location": "HttpSteps.groovy:31"
});
formatter.result({
  "duration": 6427760,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "404",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:35"
});
formatter.result({
  "duration": 623236,
  "error_message": "Assertion failed: \n\nassert response.status \u003d\u003d statusCode\n       |        |      |  |\n       |        200    |  404\n       |               false\n       GET http://localhost:8080/api/v1/artifacts/named/cucumber-artifact returned a response status of 200 OK\n\n\tat HttpSteps$_run_closure5.doCall(HttpSteps.groovy:36)\n\tat ✽.Then the response should be 404(api/artifact.feature:30)\n",
  "status": "failed"
});
formatter.after({
  "duration": 17830193,
  "status": "passed"
});
formatter.uri("api/health.feature");
formatter.feature({
  "line": 1,
  "name": "The health endpoint",
  "description": "\nAs a DeployDB administrator\nI should be able to view the health of the application",
  "id": "the-health-endpoint",
  "keyword": "Feature"
});
formatter.before({
  "duration": 361477386,
  "status": "passed"
});
formatter.scenario({
  "line": 6,
  "name": "Fetching health under normal circumstances",
  "description": "\nAssuming the application is up and running properly, the built-in\nhealthcheck endpoint should return information about all the configured\nhealthchecks currently running inside of DeployDB",
  "id": "the-health-endpoint;fetching-health-under-normal-circumstances",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 12,
  "name": "I GET \"/healthcheck\" from the admin app",
  "keyword": "When "
});
formatter.step({
  "line": 13,
  "name": "the response should be 200",
  "keyword": "Then "
});
formatter.step({
  "line": 14,
  "name": "the response body should be:",
  "keyword": "And ",
  "doc_string": {
    "content_type": "",
    "line": 15,
    "value": "{\"deadlocks\":{\"healthy\":true},\"sanity\":{\"healthy\":true}}"
  }
});
formatter.match({
  "arguments": [
    {
      "val": "/healthcheck",
      "offset": 7
    }
  ],
  "location": "HttpSteps.groovy:27"
});
formatter.result({
  "duration": 29472442,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:35"
});
formatter.result({
  "duration": 163690,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:39"
});
formatter.result({
  "duration": 2015658,
  "status": "passed"
});
formatter.after({
  "duration": 8923897,
  "status": "passed"
});
});