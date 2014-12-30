$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("api/artifact.feature");
formatter.feature({
  "line": 1,
  "name": "Artifact APIs",
  "description": "\nAs a RESTful client or user\nI should be able to read information about artifacts",
  "id": "artifact-apis",
  "keyword": "Feature"
});
formatter.before({
  "duration": 4126460654,
  "status": "passed"
});
formatter.before({
  "duration": 4863075,
  "status": "passed"
});
formatter.scenario({
  "line": 8,
  "name": "Fetching an artifact by ID that exists",
  "description": "",
  "id": "artifact-apis;fetching-an-artifact-by-id-that-exists",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 7,
      "name": "@freezetime"
    }
  ]
});
formatter.step({
  "line": 10,
  "name": "there is an artifact",
  "keyword": "Given "
});
formatter.step({
  "line": 11,
  "name": "I GET \"/api/v1/artifacts/1\"",
  "keyword": "When "
});
formatter.step({
  "line": 12,
  "name": "the response should be 200",
  "keyword": "Then "
});
formatter.step({
  "line": 13,
  "name": "the body should be JSON:",
  "keyword": "And ",
  "doc_string": {
    "content_type": "",
    "line": 14,
    "value": "  {\n    \"id\" : 1,\n    \"group\" : \"com.example.cucumber\",\n    \"name\" : \"cucumber-artifact\",\n    \"version\" : \"1.0.1\",\n    \"sourceUrl\" : \"http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.1/cucumber-artifact-1.0.1.jar\",\n    \"createdAt\" : \"{{created_timestamp}}\"\n  }"
  }
});
formatter.match({
  "location": "ArtifactSteps.groovy:8"
});
formatter.result({
  "duration": 204520969,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/artifacts/1",
      "offset": 7
    }
  ],
  "location": "HttpSteps.groovy:16"
});
formatter.result({
  "duration": 460848658,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:20"
});
formatter.result({
  "duration": 12179109,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:28"
});
formatter.result({
  "duration": 150206361,
  "status": "passed"
});
formatter.after({
  "duration": 44385678,
  "status": "passed"
});
formatter.after({
  "duration": 647873,
  "status": "passed"
});
formatter.before({
  "duration": 610893641,
  "status": "passed"
});
formatter.scenario({
  "line": 25,
  "name": "Fetching an artifact by ID that doesn\u0027t exist",
  "description": "",
  "id": "artifact-apis;fetching-an-artifact-by-id-that-doesn\u0027t-exist",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 27,
  "name": "I GET \"/api/v1/artifacts/1\"",
  "keyword": "When "
});
formatter.step({
  "line": 28,
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
  "location": "HttpSteps.groovy:16"
});
formatter.result({
  "duration": 153750454,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "404",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:20"
});
formatter.result({
  "duration": 259479,
  "status": "passed"
});
formatter.after({
  "duration": 25047709,
  "status": "passed"
});
formatter.before({
  "duration": 538431147,
  "status": "passed"
});
formatter.scenario({
  "line": 38,
  "name": "Fetching an artifact by the name that doesn\u0027t exist",
  "description": "",
  "id": "artifact-apis;fetching-an-artifact-by-the-name-that-doesn\u0027t-exist",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 40,
  "name": "I GET \"/api/v1/artifacts/named/cucumber-artifact\"",
  "keyword": "When "
});
formatter.step({
  "line": 41,
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
  "location": "HttpSteps.groovy:16"
});
formatter.result({
  "duration": 211027945,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "404",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:20"
});
formatter.result({
  "duration": 190635,
  "status": "passed"
});
formatter.after({
  "duration": 11485761,
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
  "duration": 410089579,
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
  "location": "HttpSteps.groovy:12"
});
formatter.result({
  "duration": 150336234,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:20"
});
formatter.result({
  "duration": 157866,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:28"
});
formatter.result({
  "duration": 1399348,
  "status": "passed"
});
formatter.after({
  "duration": 15226731,
  "status": "passed"
});
});