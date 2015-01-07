$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("api/artifact/creating.feature");
formatter.feature({
  "line": 1,
  "name": "Artifact CREATE APIs",
  "description": "\nAs a RESTful client or user\nI should be able to create new artifacts in the system",
  "id": "artifact-create-apis",
  "keyword": "Feature"
});
formatter.before({
  "duration": 4112080266,
  "status": "passed"
});
formatter.before({
  "duration": 6116083,
  "status": "passed"
});
formatter.scenario({
  "line": 8,
  "name": "Creating a new artifact",
  "description": "\nThe creation of an artifact technically only requires group, name and\nversion. However it\u0027s a good idea to always provide a `sourceUrl` as well\nin order future users of DeployDB to be able to backtrack an artifact all\nthe way to the binary itself",
  "id": "artifact-create-apis;creating-a-new-artifact",
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
  "line": 15,
  "name": "I PUT to \"/api/v1/artifacts\" with:",
  "keyword": "When ",
  "doc_string": {
    "content_type": "",
    "line": 16,
    "value": "  {\n    \"group\" : \"com.example.cucumber\",\n    \"name\" : \"cukes\",\n    \"version\" : \"1.2.345\"\n  }"
  }
});
formatter.step({
  "line": 23,
  "name": "the response should be 201",
  "keyword": "Then "
});
formatter.step({
  "line": 24,
  "name": "the body should be JSON:",
  "keyword": "And ",
  "doc_string": {
    "content_type": "",
    "line": 25,
    "value": "  {\n    \"id\" : 1,\n    \"group\" : \"com.example.cucumber\",\n    \"name\" : \"cukes\",\n    \"version\" : \"1.2.345\",\n    \"sourceUrl\" : null,\n    \"createdAt\" : \"{{created_timestamp}}\"\n  }"
  }
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/artifacts",
      "offset": 10
    }
  ],
  "location": "HttpSteps.groovy:24"
});
formatter.result({
  "duration": 785391284,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "201",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:33"
});
formatter.result({
  "duration": 3839425,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:41"
});
formatter.result({
  "duration": 58915650,
  "status": "passed"
});
formatter.after({
  "duration": 39923287,
  "status": "passed"
});
formatter.after({
  "duration": 737777,
  "status": "passed"
});
formatter.uri("api/artifact/deleting.feature");
formatter.feature({
  "line": 1,
  "name": "Artifact DELETE APIs",
  "description": "\nAs a RESTful client or user\nI should be *not* be able to delete artifacts",
  "id": "artifact-delete-apis",
  "keyword": "Feature"
});
formatter.before({
  "duration": 442108152,
  "status": "passed"
});
formatter.scenario({
  "line": 7,
  "name": "Attempting to delete an artifact",
  "description": "\nOnce artifacts are in DeployDB they\u0027re there forever. FOREVER.",
  "id": "artifact-delete-apis;attempting-to-delete-an-artifact",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 11,
  "name": "there is an artifact",
  "keyword": "Given "
});
formatter.step({
  "line": 12,
  "name": "I DELETE \"/api/v1/artifacts/1\"",
  "keyword": "When "
});
formatter.step({
  "line": 13,
  "name": "the response should be 405",
  "keyword": "Then "
});
formatter.match({
  "location": "ArtifactSteps.groovy:8"
});
formatter.result({
  "duration": 35577285,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/artifacts/1",
      "offset": 10
    }
  ],
  "location": "HttpSteps.groovy:20"
});
formatter.result({
  "duration": 45924948,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "405",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:33"
});
formatter.result({
  "duration": 270272,
  "status": "passed"
});
formatter.after({
  "duration": 40410197,
  "status": "passed"
});
formatter.uri("api/artifact/reading.feature");
formatter.feature({
  "line": 1,
  "name": "Artifact READ APIs",
  "description": "\nAs a RESTful client or user\nI should be able to read information about artifacts",
  "id": "artifact-read-apis",
  "keyword": "Feature"
});
formatter.before({
  "duration": 380972950,
  "status": "passed"
});
formatter.before({
  "duration": 86241,
  "status": "passed"
});
formatter.scenario({
  "line": 8,
  "name": "Fetching an artifact by ID that exists",
  "description": "",
  "id": "artifact-read-apis;fetching-an-artifact-by-id-that-exists",
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
  "duration": 2960613,
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
  "duration": 66143555,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:33"
});
formatter.result({
  "duration": 174674,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:41"
});
formatter.result({
  "duration": 2345745,
  "status": "passed"
});
formatter.after({
  "duration": 29306476,
  "status": "passed"
});
formatter.after({
  "duration": 60148,
  "status": "passed"
});
formatter.before({
  "duration": 364252845,
  "status": "passed"
});
formatter.scenario({
  "line": 25,
  "name": "Fetching an artifact by ID that doesn\u0027t exist",
  "description": "",
  "id": "artifact-read-apis;fetching-an-artifact-by-id-that-doesn\u0027t-exist",
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
  "duration": 53425333,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "404",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:33"
});
formatter.result({
  "duration": 171511,
  "status": "passed"
});
formatter.after({
  "duration": 32432781,
  "status": "passed"
});
formatter.before({
  "duration": 280043021,
  "status": "passed"
});
formatter.scenario({
  "line": 31,
  "name": "Fetching an artifact by a non-numeric ID",
  "description": "",
  "id": "artifact-read-apis;fetching-an-artifact-by-a-non-numeric-id",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 30,
      "name": "@error"
    }
  ]
});
formatter.step({
  "line": 33,
  "name": "I GET \"/api/v1/artifacts/0xdeadbeef\"",
  "keyword": "When "
});
formatter.step({
  "line": 34,
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
  "duration": 41799121,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "400",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:33"
});
formatter.result({
  "duration": 178845,
  "status": "passed"
});
formatter.after({
  "duration": 23998514,
  "status": "passed"
});
formatter.before({
  "duration": 262260575,
  "status": "passed"
});
formatter.before({
  "duration": 96127,
  "status": "passed"
});
formatter.scenario({
  "line": 38,
  "name": "Fetching an artifact by the (group, name) tuple",
  "description": "",
  "id": "artifact-read-apis;fetching-an-artifact-by-the-(group,-name)-tuple",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 37,
      "name": "@freezetime"
    }
  ]
});
formatter.step({
  "line": 40,
  "name": "there is an artifact",
  "keyword": "Given "
});
formatter.step({
  "line": 41,
  "name": "I GET \"/api/v1/artifacts/by-module/com.example.cucumber:cucumber-artifact\"",
  "keyword": "When "
});
formatter.step({
  "line": 42,
  "name": "the response should be 200",
  "keyword": "Then "
});
formatter.step({
  "line": 43,
  "name": "the body should be JSON:",
  "keyword": "And ",
  "doc_string": {
    "content_type": "",
    "line": 44,
    "value": "  {\n    \"id\" : 1,\n    \"group\" : \"com.example.cucumber\",\n    \"name\" : \"cucumber-artifact\",\n    \"version\" : \"1.0.1\",\n    \"sourceUrl\" : \"http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.1/cucumber-artifact-1.0.1.jar\",\n    \"createdAt\" : \"{{created_timestamp}}\"\n  }"
  }
});
formatter.match({
  "location": "ArtifactSteps.groovy:8"
});
formatter.result({
  "duration": 2835138,
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
  "duration": 107012240,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:33"
});
formatter.result({
  "duration": 185554,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:41"
});
formatter.result({
  "duration": 2321983,
  "status": "passed"
});
formatter.after({
  "duration": 35655620,
  "status": "passed"
});
formatter.after({
  "duration": 29419,
  "status": "passed"
});
formatter.before({
  "duration": 295508905,
  "status": "passed"
});
formatter.scenario({
  "line": 55,
  "name": "Fetching an artifact by the (group, name) tuple that doesn\u0027t exist",
  "description": "",
  "id": "artifact-read-apis;fetching-an-artifact-by-the-(group,-name)-tuple-that-doesn\u0027t-exist",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 57,
  "name": "I GET \"/api/v1/artifacts/by-module/com.example.cucumber:cucumber-artifact\"",
  "keyword": "When "
});
formatter.step({
  "line": 58,
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
  "duration": 40310873,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "404",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:33"
});
formatter.result({
  "duration": 191585,
  "status": "passed"
});
formatter.after({
  "duration": 31382320,
  "status": "passed"
});
formatter.uri("api/artifact/updating.feature");
formatter.feature({
  "line": 1,
  "name": "Artifact UPDATE APIs",
  "description": "\nAs a RESTful client or user\nI should be *not* be able to update artifacts",
  "id": "artifact-update-apis",
  "keyword": "Feature"
});
formatter.before({
  "duration": 285339366,
  "status": "passed"
});
formatter.scenario({
  "line": 7,
  "name": "Attempting to update an artifact",
  "description": "\nArtifacts must be considered immutable to ensure consistency of data from\nDeployDB. We can\u0027t different binaries with the same version, that\u0027s silly.",
  "id": "artifact-update-apis;attempting-to-update-an-artifact",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 12,
  "name": "there is an artifact",
  "keyword": "Given "
});
formatter.step({
  "line": 13,
  "name": "I PATCH \"/api/v1/artifacts/1\" with:",
  "keyword": "When ",
  "doc_string": {
    "content_type": "",
    "line": 14,
    "value": "  {\n    \"version\" : \"9.0.0.0\"\n  }"
  }
});
formatter.step({
  "line": 19,
  "name": "the response should be 405",
  "keyword": "Then "
});
formatter.match({
  "location": "ArtifactSteps.groovy:8"
});
formatter.result({
  "duration": 2404344,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/artifacts/1",
      "offset": 9
    }
  ],
  "location": "HttpSteps.groovy:28"
});
formatter.result({
  "duration": 34521728,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "405",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:33"
});
formatter.result({
  "duration": 185748,
  "status": "passed"
});
formatter.after({
  "duration": 28448045,
  "status": "passed"
});
formatter.uri("api/deployment/reading.feature");
formatter.feature({
  "line": 1,
  "name": "Deployment READ APIs",
  "description": "\nAs a RESTful client or user\nI should be able to read information about deployments",
  "id": "deployment-read-apis",
  "keyword": "Feature"
});
formatter.before({
  "duration": 291813517,
  "status": "passed"
});
formatter.before({
  "duration": 68903,
  "status": "passed"
});
formatter.scenario({
  "line": 8,
  "name": "Fetching a deployment by ID",
  "description": "",
  "id": "deployment-read-apis;fetching-a-deployment-by-id",
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
  "name": "there is a deployment",
  "keyword": "Given "
});
formatter.step({
  "line": 11,
  "name": "I GET \"/api/v1/deployments/1\"",
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
    "value": "  {\n    \"id\" : 1,\n    \"artifact\" : {\n      \"id\" : 1,\n      \"group\" : \"com.example.cucumber\",\n      \"name\" : \"cucumber-artifact\",\n      \"version\" : \"1.0.1\",\n      \"sourceUrl\" : null,\n      \"createdAt\" : \"{{created_timestamp}}\"\n    },\n    \"environment\" : \"pre-production\",\n    \"createdAt\" : \"{{created_timestamp}}\"\n  }"
  }
});
formatter.match({
  "location": "DeploymentSteps.groovy:8"
});
formatter.result({
  "duration": 7720671,
  "status": "passed"
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
  "duration": 40501892,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:33"
});
formatter.result({
  "duration": 175597,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:41"
});
formatter.result({
  "duration": 9953400,
  "status": "passed"
});
formatter.after({
  "duration": 7960353,
  "status": "passed"
});
formatter.after({
  "duration": 31421,
  "status": "passed"
});
formatter.before({
  "duration": 311954190,
  "status": "passed"
});
formatter.scenario({
  "line": 30,
  "name": "Fetching a deployment by ID that doesn\u0027t exist",
  "description": "",
  "id": "deployment-read-apis;fetching-a-deployment-by-id-that-doesn\u0027t-exist",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 32,
  "name": "I GET \"/api/v1/deployments/1\"",
  "keyword": "When "
});
formatter.step({
  "line": 33,
  "name": "the response should be 404",
  "keyword": "Then "
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
  "duration": 36862794,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "404",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:33"
});
formatter.result({
  "duration": 206029,
  "status": "passed"
});
formatter.after({
  "duration": 15208069,
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
  "duration": 230327814,
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
  "duration": 69221205,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:33"
});
formatter.result({
  "duration": 214437,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:41"
});
formatter.result({
  "duration": 1714001,
  "status": "passed"
});
formatter.after({
  "duration": 27694061,
  "status": "passed"
});
});