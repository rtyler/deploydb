$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("api/artifact.feature");
formatter.feature({
  "line": 1,
  "name": "Artifact APIs",
  "description": "\nAs a RESTful client or user\nI should be able to read information about artifacts",
  "id": "artifact-apis",
  "keyword": "Feature"
});
formatter.before({
  "duration": 4337321227,
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
  "name": "there is an artifact",
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
formatter.step({
  "line": 12,
  "name": "the body should be JSON:",
  "keyword": "And ",
  "doc_string": {
    "content_type": "",
    "line": 13,
    "value": "  {\n    \"id\" : 1,\n    \"group\" : \"com.example.cucumber\",\n    \"name\" : \"cucumber-artifact\",\n    \"version\" : \"1.0.1\",\n    \"sourceUrl\" : \"http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.1/cucumber-artifact-1.0.1.jar\"\n  }"
  }
});
formatter.match({
  "location": "ArtifactSteps.groovy:8"
});
formatter.result({
  "duration": 310995127,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/artifacts/1",
      "offset": 7
    }
  ],
  "location": "HttpSteps.groovy:14"
});
formatter.result({
  "duration": 492235203,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:18"
});
formatter.result({
  "duration": 14131016,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:26"
});
formatter.result({
  "duration": 82445793,
  "error_message": "Assertion failed: \n\nassert bodyNode \u003d\u003d expectedNode\n       |        |  |\n       |        |  {\"id\":1,\"group\":\"com.example.cucumber\",\"name\":\"cucumber-artifact\",\"version\":\"1.0.1\",\"sourceUrl\":\"http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.1/cucumber-artifact-1.0.1.jar\"}\n       |        false\n       {\"id\":1,\"group\":\"com.example.cucumber\",\"name\":\"cucumber-artifact\"}\n\n\tat HttpSteps$_run_closure5.doCall(HttpSteps.groovy:32)\n\tat ✽.And the body should be JSON:(api/artifact.feature:12)\n",
  "status": "failed"
});
formatter.after({
  "duration": 31842087,
  "status": "passed"
});
formatter.before({
  "duration": 611908308,
  "status": "passed"
});
formatter.scenario({
  "line": 23,
  "name": "Fetching an artifact by ID that doesn\u0027t exist",
  "description": "",
  "id": "artifact-apis;fetching-an-artifact-by-id-that-doesn\u0027t-exist",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 25,
  "name": "I GET \"/api/v1/artifacts/1\"",
  "keyword": "When "
});
formatter.step({
  "line": 26,
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
  "location": "HttpSteps.groovy:14"
});
formatter.result({
  "duration": 229650642,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "404",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:18"
});
formatter.result({
  "duration": 276199,
  "status": "passed"
});
formatter.after({
  "duration": 40629712,
  "status": "passed"
});
formatter.before({
  "duration": 693448396,
  "status": "passed"
});
formatter.scenario({
  "line": 36,
  "name": "Fetching an artifact by the name that doesn\u0027t exist",
  "description": "",
  "id": "artifact-apis;fetching-an-artifact-by-the-name-that-doesn\u0027t-exist",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 38,
  "name": "I GET \"/api/v1/artifacts/named/cucumber-artifact\"",
  "keyword": "When "
});
formatter.step({
  "line": 39,
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
  "location": "HttpSteps.groovy:14"
});
formatter.result({
  "duration": 99345305,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "404",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:18"
});
formatter.result({
  "duration": 336500,
  "status": "passed"
});
formatter.after({
  "duration": 11594088,
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
  "duration": 456162822,
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
  "name": "the body should be JSON:",
  "keyword": "And ",
  "doc_string": {
    "content_type": "",
    "line": 15,
    "value": "{\n  \"deadlocks\" : {\n    \"healthy\" : true\n  },\n  \"sanity\" : {\n    \"healthy\" : true\n  },\n  \"hibernate\" : {\n    \"healthy\" : true\n  }\n}"
  }
});
formatter.match({
  "arguments": [
    {
      "val": "/healthcheck",
      "offset": 7
    }
  ],
  "location": "HttpSteps.groovy:10"
});
formatter.result({
  "duration": 147817304,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:18"
});
formatter.result({
  "duration": 195878,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:26"
});
formatter.result({
  "duration": 1198052,
  "status": "passed"
});
formatter.after({
  "duration": 25327349,
  "status": "passed"
});
});