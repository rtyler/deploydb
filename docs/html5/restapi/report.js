$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("api/artifact.feature");
formatter.feature({
  "line": 1,
  "name": "Artifact APIs",
  "description": "\nAs a RESTful client or user\nI should be able to read information about artifacts",
  "id": "artifact-apis",
  "keyword": "Feature"
});
formatter.before({
  "duration": 3198629594,
  "status": "passed"
});
formatter.before({
  "duration": 5455091,
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
  "duration": 290819975,
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
  "duration": 406022797,
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
  "duration": 11565733,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:28"
});
formatter.result({
  "duration": 121132611,
  "status": "passed"
});
formatter.after({
  "duration": 25169474,
  "status": "passed"
});
formatter.after({
  "duration": 702787,
  "status": "passed"
});
formatter.before({
  "duration": 660001956,
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
  "duration": 167979152,
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
  "duration": 238771,
  "status": "passed"
});
formatter.after({
  "duration": 15958107,
  "status": "passed"
});
formatter.before({
  "duration": 371924737,
  "status": "passed"
});
formatter.scenario({
  "line": 30,
  "name": "Fetching an artifact by a non-numeric ID",
  "description": "",
  "id": "artifact-apis;fetching-an-artifact-by-a-non-numeric-id",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 32,
  "name": "I GET \"/api/v1/artifacts/0xdeadbeef\"",
  "keyword": "When "
});
formatter.step({
  "line": 33,
  "name": "the response should be 400",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/artifacts/0xdeadbeef",
      "offset": 7
    }
  ],
  "location": "HttpSteps.groovy:16"
});
formatter.result({
  "duration": 108167936,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "400",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:20"
});
formatter.result({
  "duration": 176402,
  "status": "passed"
});
formatter.after({
  "duration": 6718906,
  "status": "passed"
});
formatter.before({
  "duration": 343468960,
  "status": "passed"
});
formatter.before({
  "duration": 99867,
  "status": "passed"
});
formatter.scenario({
  "line": 37,
  "name": "Fetching an artifact by the (group, name) tuple",
  "description": "",
  "id": "artifact-apis;fetching-an-artifact-by-the-(group,-name)-tuple",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 36,
      "name": "@freezetime"
    }
  ]
});
formatter.step({
  "line": 39,
  "name": "there is an artifact",
  "keyword": "Given "
});
formatter.step({
  "line": 40,
  "name": "I GET \"/api/v1/artifacts/by-module/com.example.cucumber:cucumber-artifact\"",
  "keyword": "When "
});
formatter.step({
  "line": 41,
  "name": "the response should be 200",
  "keyword": "Then "
});
formatter.step({
  "line": 42,
  "name": "the body should be JSON:",
  "keyword": "And ",
  "doc_string": {
    "content_type": "",
    "line": 43,
    "value": "  {\n    \"id\" : 1,\n    \"group\" : \"com.example.cucumber\",\n    \"name\" : \"cucumber-artifact\",\n    \"version\" : \"1.0.1\",\n    \"sourceUrl\" : \"http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.1/cucumber-artifact-1.0.1.jar\",\n    \"createdAt\" : \"{{created_timestamp}}\"\n  }"
  }
});
formatter.match({
  "location": "ArtifactSteps.groovy:8"
});
formatter.result({
  "duration": 6384220,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/artifacts/by-module/com.example.cucumber:cucumber-artifact",
      "offset": 7
    }
  ],
  "location": "HttpSteps.groovy:16"
});
formatter.result({
  "duration": 157092191,
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
  "duration": 173917,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:28"
});
formatter.result({
  "duration": 2523783,
  "status": "passed"
});
formatter.after({
  "duration": 10111021,
  "status": "passed"
});
formatter.after({
  "duration": 54811,
  "status": "passed"
});
formatter.before({
  "duration": 371327438,
  "status": "passed"
});
formatter.scenario({
  "line": 54,
  "name": "Fetching an artifact by the (group, name) tuple that doesn\u0027t exist",
  "description": "",
  "id": "artifact-apis;fetching-an-artifact-by-the-(group,-name)-tuple-that-doesn\u0027t-exist",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 56,
  "name": "I GET \"/api/v1/artifacts/by-module/com.example.cucumber:cucumber-artifact\"",
  "keyword": "When "
});
formatter.step({
  "line": 57,
  "name": "the response should be 404",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/artifacts/by-module/com.example.cucumber:cucumber-artifact",
      "offset": 7
    }
  ],
  "location": "HttpSteps.groovy:16"
});
formatter.result({
  "duration": 126269791,
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
  "duration": 169899,
  "status": "passed"
});
formatter.after({
  "duration": 10564140,
  "status": "passed"
});
formatter.uri("api/deployment.feature");
formatter.feature({
  "line": 1,
  "name": "Deployment Artifact APIs",
  "description": "\nAs a RESTful client or user\nI should be able to create deployments\nAnd I should be able to read about them",
  "id": "deployment-artifact-apis",
  "keyword": "Feature"
});
formatter.before({
  "duration": 326766079,
  "status": "passed"
});
formatter.before({
  "duration": 81618,
  "status": "passed"
});
formatter.scenario({
  "line": 9,
  "name": "Fetching a deployment by ID",
  "description": "",
  "id": "deployment-artifact-apis;fetching-a-deployment-by-id",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 8,
      "name": "@freezetime"
    }
  ]
});
formatter.step({
  "line": 11,
  "name": "there is a deployment",
  "keyword": "Given "
});
formatter.step({
  "line": 12,
  "name": "I GET \"/api/v1/deployments/1\"",
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
    "value": "  {\n    \"id\" : 1,\n    \"artifact\" : {\n      \"id\" : 1,\n      \"group\" : \"com.example.cucumber\",\n      \"name\" : \"cucumber-artifact\",\n      \"version\" : \"1.0.1\"\n    },\n    \"environment\" : {\n      \"name\" : \"pre-production\"\n    },\n    \"service\" : {\n      \"id\" : 1\n    },\n    \"createdAt\" : \"{{created_timestamp}}\"\n  }"
  }
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/deployments/1",
      "offset": 7
    }
  ],
  "location": "HttpSteps.groovy:16"
});
formatter.result({
  "status": "skipped"
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
  "status": "skipped"
});
formatter.match({
  "location": "HttpSteps.groovy:28"
});
formatter.result({
  "status": "skipped"
});
formatter.after({
  "duration": 8030380,
  "status": "passed"
});
formatter.after({
  "duration": 32735,
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
  "duration": 326987224,
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
    "value": "{\n  \"deadlocks\" : {\n    \"healthy\" : true\n  },\n  \"sanity\" : {\n    \"healthy\" : true\n  },\n  \"hibernate\" : {\n    \"healthy\" : true\n  },\n  \"webhook\" : {\n    \"healthy\" : true\n  }\n}"
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
  "duration": 138548683,
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
  "duration": 174111,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:28"
});
formatter.result({
  "duration": 1368049,
  "status": "passed"
});
formatter.after({
  "duration": 21228822,
  "status": "passed"
});
});