$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("api/artifact/creating.feature");
formatter.feature({
  "line": 1,
  "name": "Artifact CREATE APIs",
  "description": "\nAs a RESTful client or user\nI should be able to create new artifacts in the system",
  "id": "artifact-create-apis",
  "keyword": "Feature"
});
formatter.before({
  "duration": 4365157511,
  "status": "passed"
});
formatter.before({
  "duration": 5803992,
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
  "duration": 855713869,
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
  "duration": 5255968,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:41"
});
formatter.result({
  "duration": 99873787,
  "status": "passed"
});
formatter.after({
  "duration": 50330159,
  "status": "passed"
});
formatter.after({
  "duration": 736258,
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
  "duration": 511693771,
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
  "duration": 61066722,
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
  "duration": 77116706,
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
  "duration": 216807,
  "status": "passed"
});
formatter.after({
  "duration": 62262076,
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
  "duration": 525317179,
  "status": "passed"
});
formatter.before({
  "duration": 93044,
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
  "duration": 3026012,
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
  "duration": 143569755,
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
  "duration": 205273,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:41"
});
formatter.result({
  "duration": 3013652,
  "status": "passed"
});
formatter.after({
  "duration": 6835488,
  "status": "passed"
});
formatter.after({
  "duration": 50777,
  "status": "passed"
});
formatter.before({
  "duration": 385564595,
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
  "duration": 50533237,
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
  "duration": 174221,
  "status": "passed"
});
formatter.after({
  "duration": 28520576,
  "status": "passed"
});
formatter.before({
  "duration": 303184354,
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
  "duration": 35359525,
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
  "duration": 187262,
  "status": "passed"
});
formatter.after({
  "duration": 22576243,
  "status": "passed"
});
formatter.before({
  "duration": 309725011,
  "status": "passed"
});
formatter.before({
  "duration": 52006,
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
  "duration": 2473921,
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
  "duration": 138640662,
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
  "duration": 173312,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:41"
});
formatter.result({
  "duration": 3304602,
  "status": "passed"
});
formatter.after({
  "duration": 11519497,
  "status": "passed"
});
formatter.after({
  "duration": 28189,
  "status": "passed"
});
formatter.before({
  "duration": 297543016,
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
  "duration": 40943992,
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
  "duration": 165329,
  "status": "passed"
});
formatter.after({
  "duration": 55264098,
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
  "duration": 293171143,
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
  "duration": 2544495,
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
  "duration": 42656084,
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
  "duration": 159082,
  "status": "passed"
});
formatter.after({
  "duration": 51609940,
  "status": "passed"
});
formatter.uri("api/deployment/creating.feature");
formatter.feature({
  "line": 1,
  "name": "Deployment CREATE APIs",
  "description": "\nAs a RESTful client or user\nI should be able to create new deployments in the system",
  "id": "deployment-create-apis",
  "keyword": "Feature"
});
formatter.before({
  "duration": 375348724,
  "status": "passed"
});
formatter.before({
  "duration": 74427,
  "status": "passed"
});
formatter.scenario({
  "line": 8,
  "name": "Creating a new deployment",
  "description": "\nCreating a deployment assumes that we\u0027ve already got an Artifact stored\ninside the DeployDB database",
  "id": "deployment-create-apis;creating-a-new-deployment",
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
  "line": 13,
  "name": "there is an artifact",
  "keyword": "Given "
});
formatter.step({
  "line": 14,
  "name": "I PUT to \"/api/v1/deployments\" with:",
  "keyword": "When ",
  "doc_string": {
    "content_type": "",
    "line": 15,
    "value": "  {\n    \"artifactId\" : 1,\n    \"environment\" : \"pre-production\"\n  }"
  }
});
formatter.step({
  "line": 21,
  "name": "the response should be 201",
  "keyword": "Then "
});
formatter.step({
  "line": 22,
  "name": "the body should be JSON:",
  "keyword": "And ",
  "doc_string": {
    "content_type": "",
    "line": 23,
    "value": "  {\n    \"id\" : 1,\n    \"artifact\" : {\n      \"id\" : 1,\n      \"group\" : \"com.example.cucumber\",\n      \"name\" : \"cucumber-artifact\",\n      \"version\" : \"1.0.1\",\n      \"sourceUrl\" : \"http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.1/cucumber-artifact-1.0.1.jar\",\n      \"createdAt\" : \"{{created_timestamp}}\"\n    },\n    \"environment\" : \"pre-production\",\n    \"status\" : \"STARTED\",\n    \"createdAt\" : \"{{created_timestamp}}\"\n  }"
  }
});
formatter.match({
  "location": "ArtifactSteps.groovy:8"
});
formatter.result({
  "duration": 2852157,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/deployments",
      "offset": 10
    }
  ],
  "location": "HttpSteps.groovy:24"
});
formatter.result({
  "duration": 56041508,
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
  "duration": 174109,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:41"
});
formatter.result({
  "duration": 4970470,
  "status": "passed"
});
formatter.after({
  "duration": 22379573,
  "status": "passed"
});
formatter.after({
  "duration": 38867,
  "status": "passed"
});
formatter.before({
  "duration": 387718584,
  "status": "passed"
});
formatter.before({
  "duration": 73225,
  "status": "passed"
});
formatter.scenario({
  "line": 41,
  "name": "Updating a deployment with a status change",
  "description": "",
  "id": "deployment-create-apis;updating-a-deployment-with-a-status-change",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 40,
      "name": "@freezetime"
    }
  ]
});
formatter.step({
  "line": 43,
  "name": "there is a deployment",
  "keyword": "Given "
});
formatter.step({
  "line": 44,
  "name": "I PATCH \"/api/v1/deployments/1\" with:",
  "keyword": "When ",
  "doc_string": {
    "content_type": "",
    "line": 45,
    "value": "  {\n    \"status\" : \"FAILED\"\n  }"
  }
});
formatter.step({
  "line": 50,
  "name": "the response should be 200",
  "keyword": "Then "
});
formatter.step({
  "line": 51,
  "name": "the body should be JSON:",
  "keyword": "And ",
  "doc_string": {
    "content_type": "",
    "line": 52,
    "value": "  {\n    \"id\" : 1,\n    \"artifact\" : {\n      \"id\" : 1,\n      \"group\" : \"com.example.cucumber\",\n      \"name\" : \"cucumber-artifact\",\n      \"version\" : \"1.0.1\",\n      \"sourceUrl\" : \"http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.1/cucumber-artifact-1.0.1.jar\",\n      \"createdAt\" : \"{{created_timestamp}}\"\n    },\n    \"environment\" : \"pre-production\",\n    \"status\" : \"failed\",\n    \"createdAt\" : \"{{created_timestamp}}\"\n  }"
  }
});
formatter.match({
  "location": "DeploymentSteps.groovy:8"
});
formatter.result({
  "duration": 8320334,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/deployments/1",
      "offset": 9
    }
  ],
  "location": "HttpSteps.groovy:28"
});
formatter.result({
  "duration": 26105679,
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
  "duration": 5953929,
  "error_message": "Assertion failed: \n\nassert response.status \u003d\u003d statusCode\n       |        |      |  |\n       |        405    |  200\n       |               false\n       InboundJaxrsResponse{ClientResponse{method\u003dPATCH, uri\u003dhttp://localhost:8080/api/v1/deployments/1, status\u003d405, reason\u003dMethod Not Allowed}}\n\n\tat HttpSteps$_run_closure6.doCall(HttpSteps.groovy:34)\n\tat ✽.Then the response should be 200(api/deployment/creating.feature:50)\n",
  "status": "failed"
});
formatter.match({
  "location": "HttpSteps.groovy:41"
});
formatter.result({
  "status": "skipped"
});
formatter.after({
  "duration": 25135925,
  "status": "passed"
});
formatter.after({
  "duration": 31113,
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
  "duration": 328931386,
  "status": "passed"
});
formatter.before({
  "duration": 70620,
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
    "value": "  {\n    \"id\" : 1,\n    \"artifact\" : {\n      \"id\" : 1,\n      \"group\" : \"com.example.cucumber\",\n      \"name\" : \"cucumber-artifact\",\n      \"version\" : \"1.0.1\",\n      \"sourceUrl\" : null,\n      \"createdAt\" : \"{{created_timestamp}}\"\n    },\n    \"environment\" : \"pre-production\",\n    \"status\" : \"STARTED\",\n    \"createdAt\" : \"{{created_timestamp}}\"\n  }"
  }
});
formatter.match({
  "location": "DeploymentSteps.groovy:8"
});
formatter.result({
  "duration": 4190970,
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
  "duration": 43061792,
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
  "duration": 184493,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:41"
});
formatter.result({
  "duration": 2102931,
  "status": "passed"
});
formatter.after({
  "duration": 25127055,
  "status": "passed"
});
formatter.after({
  "duration": 29290,
  "status": "passed"
});
formatter.before({
  "duration": 280147537,
  "status": "passed"
});
formatter.scenario({
  "line": 31,
  "name": "Fetching a deployment by ID that doesn\u0027t exist",
  "description": "",
  "id": "deployment-read-apis;fetching-a-deployment-by-id-that-doesn\u0027t-exist",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 33,
  "name": "I GET \"/api/v1/deployments/1\"",
  "keyword": "When "
});
formatter.step({
  "line": 34,
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
  "duration": 38315840,
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
  "duration": 175115,
  "status": "passed"
});
formatter.after({
  "duration": 22209939,
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
  "duration": 293717885,
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
  "duration": 157664841,
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
  "duration": 164066,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:41"
});
formatter.result({
  "duration": 1362794,
  "status": "passed"
});
formatter.after({
  "duration": 18871945,
  "status": "passed"
});
});