$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("api/artifact.feature");
formatter.feature({
  "line": 1,
  "name": "Artifact APIs",
  "description": "\nAs a RESTful client or user\nI should be able to read information about artifacts",
  "id": "artifact-apis",
  "keyword": "Feature"
});
formatter.before({
  "duration": 4743941740,
  "status": "passed"
});
formatter.before({
  "duration": 9902980,
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
  "duration": 439611187,
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
  "duration": 537401792,
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
  "duration": 16517270,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:28"
});
formatter.result({
  "duration": 256210043,
  "status": "passed"
});
formatter.after({
  "duration": 118382557,
  "status": "passed"
});
formatter.after({
  "duration": 742373,
  "status": "passed"
});
formatter.before({
  "duration": 986975549,
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
  "duration": 130464041,
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
  "duration": 217493,
  "status": "passed"
});
formatter.after({
  "duration": 39398129,
  "status": "passed"
});
formatter.before({
  "duration": 649018494,
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
  "duration": 72848734,
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
  "duration": 189057,
  "status": "passed"
});
formatter.after({
  "duration": 49574999,
  "status": "passed"
});
formatter.before({
  "duration": 700251897,
  "status": "passed"
});
formatter.before({
  "duration": 790070,
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
  "duration": 9059150,
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
  "duration": 152215554,
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
  "duration": 172072,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:28"
});
formatter.result({
  "duration": 1627980,
  "status": "passed"
});
formatter.after({
  "duration": 20831797,
  "status": "passed"
});
formatter.after({
  "duration": 50095,
  "status": "passed"
});
formatter.before({
  "duration": 499851572,
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
  "duration": 55224678,
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
  "duration": 179099,
  "status": "passed"
});
formatter.after({
  "duration": 33700721,
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
  "duration": 644487434,
  "status": "passed"
});
formatter.before({
  "duration": 76331,
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
  "duration": 47135567,
  "status": "passed"
});
formatter.after({
  "duration": 33779,
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
  "duration": 519316059,
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
  "duration": 31516078,
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
  "duration": 190348,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:28"
});
formatter.result({
  "duration": 1683780,
  "status": "passed"
});
formatter.after({
  "duration": 32144808,
  "status": "passed"
});
});