$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("api/artifact.feature");
formatter.feature({
  "line": 1,
  "name": "Artifact APIs",
  "description": "\nAs a RESTful client or user\nI should be able to read information about artifacts",
  "id": "artifact-apis",
  "keyword": "Feature"
});
formatter.before({
  "duration": 2285440709,
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
  "name": "I GET \"/api/v1/artifacts/1\"",
  "keyword": "When "
});
formatter.step({
  "line": 10,
  "name": "the response should be 200",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/artifacts/1",
      "offset": 7
    }
  ],
  "location": "HttpSteps.groovy:32"
});
formatter.result({
  "duration": 416690938,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:36"
});
formatter.result({
  "duration": 21725621,
  "status": "passed"
});
formatter.after({
  "duration": 58760502,
  "status": "passed"
});
formatter.before({
  "duration": 676798079,
  "status": "passed"
});
formatter.scenario({
  "line": 12,
  "name": "Fetching an artifact by ID that doesn\u0027t exist",
  "description": "",
  "id": "artifact-apis;fetching-an-artifact-by-id-that-doesn\u0027t-exist",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 14,
  "name": "I GET \"/api/v1/artifacts/1\"",
  "keyword": "When "
});
formatter.step({
  "line": 15,
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
  "location": "HttpSteps.groovy:32"
});
formatter.result({
  "duration": 11726782,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "404",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:36"
});
formatter.result({
  "duration": 4315649,
  "error_message": "Assertion failed: \n\nassert response.status \u003d\u003d statusCode\n       |        |      |  |\n       |        200    |  404\n       |               false\n       GET http://localhost:8080/api/v1/artifacts/1 returned a response status of 200 OK\n\n\tat HttpSteps$_run_closure5.doCall(HttpSteps.groovy:37)\n\tat ✽.Then the response should be 404(api/artifact.feature:15)\n",
  "status": "failed"
});
formatter.after({
  "duration": 20131648,
  "status": "passed"
});
formatter.before({
  "duration": 628549716,
  "status": "passed"
});
formatter.scenario({
  "line": 18,
  "name": "Fetching an artifact by the name that doesn\u0027t exist",
  "description": "",
  "id": "artifact-apis;fetching-an-artifact-by-the-name-that-doesn\u0027t-exist",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 20,
  "name": "I GET \"/api/v1/artifacts/named/cucumber-artifact\"",
  "keyword": "When "
});
formatter.step({
  "line": 21,
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
  "location": "HttpSteps.groovy:32"
});
formatter.result({
  "duration": 8931293,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "404",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:36"
});
formatter.result({
  "duration": 620734,
  "error_message": "Assertion failed: \n\nassert response.status \u003d\u003d statusCode\n       |        |      |  |\n       |        200    |  404\n       |               false\n       GET http://localhost:8080/api/v1/artifacts/named/cucumber-artifact returned a response status of 200 OK\n\n\tat HttpSteps$_run_closure5.doCall(HttpSteps.groovy:37)\n\tat ✽.Then the response should be 404(api/artifact.feature:21)\n",
  "status": "failed"
});
formatter.after({
  "duration": 15086688,
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
  "duration": 280312946,
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
  "location": "HttpSteps.groovy:28"
});
formatter.result({
  "duration": 33926009,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:36"
});
formatter.result({
  "duration": 174529,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:40"
});
formatter.result({
  "duration": 1861289,
  "status": "passed"
});
formatter.after({
  "duration": 6979171,
  "status": "passed"
});
});