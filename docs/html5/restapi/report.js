$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("api/artifact/creating.feature");
formatter.feature({
  "id": "artifact-create-apis",
  "description": "\nAs a RESTful client or user\nI should be able to create new artifacts in the system",
  "name": "Artifact CREATE APIs",
  "keyword": "Feature",
  "line": 1
});
formatter.before({
  "duration": 2999348000,
  "status": "passed"
});
formatter.before({
  "duration": 7939000,
  "status": "passed"
});
formatter.scenario({
  "id": "artifact-create-apis;creating-a-new-artifact",
  "tags": [
    {
      "name": "@freezetime",
      "line": 7
    }
  ],
  "description": "\nThe creation of an artifact requires group, name, version and sourceUrl.",
  "name": "Creating a new artifact",
  "keyword": "Scenario",
  "line": 8,
  "type": "scenario"
});
formatter.step({
  "name": "I PUT to \"/api/v1/artifacts\" with:",
  "keyword": "When ",
  "line": 12,
  "doc_string": {
    "value": "  {\n    \"group\" : \"com.example.cucumber\",\n    \"name\" : \"cukes\",\n    \"version\" : \"1.2.345\",\n    \"sourceUrl\" : \"http://example.com/cucumber.jar\"\n  }",
    "line": 13,
    "content_type": ""
  }
});
formatter.step({
  "name": "the response should be 201",
  "keyword": "Then ",
  "line": 21
});
formatter.step({
  "name": "the body should be JSON:",
  "keyword": "And ",
  "line": 22,
  "doc_string": {
    "value": "  {\n    \"id\" : 1,\n    \"group\" : \"com.example.cucumber\",\n    \"name\" : \"cukes\",\n    \"version\" : \"1.2.345\",\n    \"sourceUrl\" : \"http://example.com/cucumber.jar\",\n    \"createdAt\" : \"{{created_timestamp}}\"\n  }",
    "line": 23,
    "content_type": ""
  }
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/artifacts",
      "offset": 10
    }
  ],
  "location": "HttpSteps.groovy:23"
});
formatter.result({
  "duration": 748355000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "201",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:54"
});
formatter.result({
  "duration": 4915000,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:62"
});
formatter.result({
  "duration": 53872000,
  "status": "passed"
});
formatter.after({
  "duration": 36301000,
  "status": "passed"
});
formatter.after({
  "duration": 550000,
  "status": "passed"
});
formatter.before({
  "duration": 604427000,
  "status": "passed"
});
formatter.scenario({
  "id": "artifact-create-apis;the-request-should-fail-when-creating-an-artifact-without-a-group-name",
  "tags": [
    {
      "name": "@error",
      "line": 34
    }
  ],
  "description": "",
  "name": "The request should fail when creating an Artifact without a group name",
  "keyword": "Scenario",
  "line": 35,
  "type": "scenario"
});
formatter.step({
  "name": "I PUT to \"/api/v1/artifacts\" with:",
  "keyword": "When ",
  "line": 37,
  "doc_string": {
    "value": "  {\n    \"name\" : \"cukes\",\n    \"version\" : \"1.2.345\",\n    \"sourceUrl\" : \"http://example.com/cucumber.jar\"\n  }",
    "line": 38,
    "content_type": ""
  }
});
formatter.step({
  "name": "the response should be 422",
  "keyword": "Then ",
  "line": 45
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/artifacts",
      "offset": 10
    }
  ],
  "location": "HttpSteps.groovy:23"
});
formatter.result({
  "duration": 71585000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "422",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:54"
});
formatter.result({
  "duration": 134000,
  "status": "passed"
});
formatter.after({
  "duration": 18588000,
  "status": "passed"
});
formatter.before({
  "duration": 544493000,
  "status": "passed"
});
formatter.scenario({
  "id": "artifact-create-apis;the-request-should-fail-when-creating-an-artifact-without-a-name",
  "tags": [
    {
      "name": "@error",
      "line": 47
    }
  ],
  "description": "",
  "name": "The request should fail when creating an Artifact without a name",
  "keyword": "Scenario",
  "line": 48,
  "type": "scenario"
});
formatter.step({
  "name": "I PUT to \"/api/v1/artifacts\" with:",
  "keyword": "When ",
  "line": 50,
  "doc_string": {
    "value": "  {\n    \"group\" : \"com.example.cucumber\",\n    \"version\" : \"1.2.345\",\n    \"sourceUrl\" : \"http://example.com/cucumber.jar\"\n  }",
    "line": 51,
    "content_type": ""
  }
});
formatter.step({
  "name": "the response should be 422",
  "keyword": "Then ",
  "line": 58
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/artifacts",
      "offset": 10
    }
  ],
  "location": "HttpSteps.groovy:23"
});
formatter.result({
  "duration": 52743000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "422",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:54"
});
formatter.result({
  "duration": 160000,
  "status": "passed"
});
formatter.after({
  "duration": 20228000,
  "status": "passed"
});
formatter.before({
  "duration": 445165000,
  "status": "passed"
});
formatter.scenario({
  "id": "artifact-create-apis;the-request-should-fail-when-creating-an-artifact-without-a-version",
  "tags": [
    {
      "name": "@error",
      "line": 60
    }
  ],
  "description": "",
  "name": "The request should fail when creating an Artifact without a version",
  "keyword": "Scenario",
  "line": 61,
  "type": "scenario"
});
formatter.step({
  "name": "I PUT to \"/api/v1/artifacts\" with:",
  "keyword": "When ",
  "line": 63,
  "doc_string": {
    "value": "  {\n    \"group\" : \"com.example.cucumber\",\n    \"name\" : \"cukes\",\n    \"sourceUrl\" : \"http://example.com/cucumber.jar\"\n  }",
    "line": 64,
    "content_type": ""
  }
});
formatter.step({
  "name": "the response should be 422",
  "keyword": "Then ",
  "line": 71
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/artifacts",
      "offset": 10
    }
  ],
  "location": "HttpSteps.groovy:23"
});
formatter.result({
  "duration": 44226000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "422",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:54"
});
formatter.result({
  "duration": 140000,
  "status": "passed"
});
formatter.after({
  "duration": 18663000,
  "status": "passed"
});
formatter.before({
  "duration": 490164000,
  "status": "passed"
});
formatter.scenario({
  "id": "artifact-create-apis;the-request-should-fail-when-creating-an-artifact-without-a-sourceurl",
  "tags": [
    {
      "name": "@error",
      "line": 73
    }
  ],
  "description": "",
  "name": "The request should fail when creating an Artifact without a sourceUrl",
  "keyword": "Scenario",
  "line": 74,
  "type": "scenario"
});
formatter.step({
  "name": "I PUT to \"/api/v1/artifacts\" with:",
  "keyword": "When ",
  "line": 76,
  "doc_string": {
    "value": "  {\n    \"group\" : \"com.example.cucumber\",\n    \"name\" : \"cukes\",\n    \"version\" : \"1.2.345\"\n  }",
    "line": 77,
    "content_type": ""
  }
});
formatter.step({
  "name": "the response should be 422",
  "keyword": "Then ",
  "line": 84
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/artifacts",
      "offset": 10
    }
  ],
  "location": "HttpSteps.groovy:23"
});
formatter.result({
  "duration": 49459000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "422",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:54"
});
formatter.result({
  "duration": 150000,
  "status": "passed"
});
formatter.after({
  "duration": 16653000,
  "status": "passed"
});
formatter.before({
  "duration": 421708000,
  "status": "passed"
});
formatter.scenario({
  "id": "artifact-create-apis;creating-an-artifact-with-too-large-of-a-group-name",
  "tags": [
    {
      "name": "@error",
      "line": 86
    }
  ],
  "description": "",
  "name": "Creating an artifact with too large of a group name",
  "keyword": "Scenario",
  "line": 87,
  "type": "scenario"
});
formatter.step({
  "name": "I have an artifact request",
  "keyword": "Given ",
  "line": 89
});
formatter.step({
  "name": "the group is over 8192 characters",
  "keyword": "And ",
  "line": 90
});
formatter.step({
  "name": "I PUT to \"/api/v1/artifacts\"",
  "keyword": "When ",
  "line": 91
});
formatter.step({
  "name": "the response should be 422",
  "keyword": "Then ",
  "line": 92
});
formatter.match({
  "location": "HttpSteps.groovy:76"
});
formatter.result({
  "duration": 111000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "group",
      "offset": 4
    },
    {
      "val": "8192",
      "offset": 18
    }
  ],
  "location": "HttpSteps.groovy:79"
});
formatter.result({
  "duration": 2826000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/artifacts",
      "offset": 10
    }
  ],
  "location": "HttpSteps.groovy:98"
});
formatter.result({
  "duration": 59578000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "422",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:54"
});
formatter.result({
  "duration": 149000,
  "status": "passed"
});
formatter.after({
  "duration": 16126000,
  "status": "passed"
});
formatter.before({
  "duration": 489346000,
  "status": "passed"
});
formatter.scenario({
  "id": "artifact-create-apis;creating-an-artifact-with-too-large-of-a-name",
  "tags": [
    {
      "name": "@error",
      "line": 94
    }
  ],
  "description": "",
  "name": "Creating an artifact with too large of a name",
  "keyword": "Scenario",
  "line": 95,
  "type": "scenario"
});
formatter.step({
  "name": "I have an artifact request",
  "keyword": "Given ",
  "line": 97
});
formatter.step({
  "name": "the name is over 8192 characters",
  "keyword": "And ",
  "line": 98
});
formatter.step({
  "name": "I PUT to \"/api/v1/artifacts\"",
  "keyword": "When ",
  "line": 99
});
formatter.step({
  "name": "the response should be 422",
  "keyword": "Then ",
  "line": 100
});
formatter.match({
  "location": "HttpSteps.groovy:76"
});
formatter.result({
  "duration": 31000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "name",
      "offset": 4
    },
    {
      "val": "8192",
      "offset": 17
    }
  ],
  "location": "HttpSteps.groovy:79"
});
formatter.result({
  "duration": 964000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/artifacts",
      "offset": 10
    }
  ],
  "location": "HttpSteps.groovy:98"
});
formatter.result({
  "duration": 42623000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "422",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:54"
});
formatter.result({
  "duration": 145000,
  "status": "passed"
});
formatter.after({
  "duration": 15310000,
  "status": "passed"
});
formatter.before({
  "duration": 440822000,
  "status": "passed"
});
formatter.scenario({
  "id": "artifact-create-apis;creating-an-artifact-with-too-large-of-a-version",
  "tags": [
    {
      "name": "@error",
      "line": 102
    }
  ],
  "description": "",
  "name": "Creating an artifact with too large of a version",
  "keyword": "Scenario",
  "line": 103,
  "type": "scenario"
});
formatter.step({
  "name": "I have an artifact request",
  "keyword": "Given ",
  "line": 105
});
formatter.step({
  "name": "the version is over 255 characters",
  "keyword": "And ",
  "line": 106
});
formatter.step({
  "name": "I PUT to \"/api/v1/artifacts\"",
  "keyword": "When ",
  "line": 107
});
formatter.step({
  "name": "the response should be 422",
  "keyword": "Then ",
  "line": 108
});
formatter.match({
  "location": "HttpSteps.groovy:76"
});
formatter.result({
  "duration": 29000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "version",
      "offset": 4
    },
    {
      "val": "255",
      "offset": 20
    }
  ],
  "location": "HttpSteps.groovy:79"
});
formatter.result({
  "duration": 482000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/artifacts",
      "offset": 10
    }
  ],
  "location": "HttpSteps.groovy:98"
});
formatter.result({
  "duration": 47908000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "422",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:54"
});
formatter.result({
  "duration": 171000,
  "status": "passed"
});
formatter.after({
  "duration": 14768000,
  "status": "passed"
});
formatter.before({
  "duration": 400090000,
  "status": "passed"
});
formatter.scenario({
  "id": "artifact-create-apis;creating-an-artifact-with-too-large-of-a-sourceurl",
  "tags": [
    {
      "name": "@error",
      "line": 110
    }
  ],
  "description": "",
  "name": "Creating an artifact with too large of a sourceUrl",
  "keyword": "Scenario",
  "line": 111,
  "type": "scenario"
});
formatter.step({
  "name": "I have an artifact request",
  "keyword": "Given ",
  "line": 113
});
formatter.step({
  "name": "the sourceUrl is over 8192 characters",
  "keyword": "And ",
  "line": 114
});
formatter.step({
  "name": "I PUT to \"/api/v1/artifacts\"",
  "keyword": "When ",
  "line": 115
});
formatter.step({
  "name": "the response should be 422",
  "keyword": "Then ",
  "line": 116
});
formatter.match({
  "location": "HttpSteps.groovy:76"
});
formatter.result({
  "duration": 28000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "sourceUrl",
      "offset": 4
    },
    {
      "val": "8192",
      "offset": 22
    }
  ],
  "location": "HttpSteps.groovy:79"
});
formatter.result({
  "duration": 665000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/artifacts",
      "offset": 10
    }
  ],
  "location": "HttpSteps.groovy:98"
});
formatter.result({
  "duration": 43523000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "422",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:54"
});
formatter.result({
  "duration": 162000,
  "status": "passed"
});
formatter.after({
  "duration": 14573000,
  "status": "passed"
});
formatter.uri("api/artifact/creatingWithWebHook.feature");
formatter.feature({
  "id": "webhook-invocation-during-create-api",
  "description": "\nAs administrator of the system, when webhooks are configured, then system should call\nappropriate webhook for CREATE API",
  "name": "Webhook invocation during CREATE API",
  "keyword": "Feature",
  "line": 1
});
formatter.before({
  "duration": 388270000,
  "status": "passed"
});
formatter.scenario({
  "id": "webhook-invocation-during-create-api;creating-an-artifact-with-webhooks-is-configured",
  "description": "",
  "name": "Creating an artifact with webhooks is configured",
  "keyword": "Scenario",
  "line": 6,
  "type": "scenario"
});
formatter.step({
  "name": "I have an webhook service",
  "keyword": "Given ",
  "line": 8
});
formatter.step({
  "name": "there is a service",
  "keyword": "Given ",
  "line": 9
});
formatter.step({
  "name": "there is a environment",
  "keyword": "Given ",
  "line": 10
});
formatter.step({
  "name": "I PUT to \"/api/v1/artifacts\"",
  "keyword": "When ",
  "line": 11
});
formatter.step({
  "name": "the webhook should be recevied with:",
  "keyword": "Then ",
  "line": 12,
  "doc_string": {
    "value": "  {\n    \"artifactId\" : 1,\n    \"deploymentId\" : 1,\n    \"serviceName\" : \"bluffdale\"\n    \"environmentName\" : \"dev-integ\"\n  }",
    "line": 13,
    "content_type": ""
  }
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/artifacts",
      "offset": 10
    }
  ],
  "location": "HttpSteps.groovy:98"
});
formatter.result({
  "status": "skipped"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.after({
  "duration": 13651000,
  "status": "passed"
});
formatter.uri("api/artifact/deleting.feature");
formatter.feature({
  "id": "artifact-delete-apis",
  "description": "\nAs a RESTful client or user\nI should be *not* be able to delete artifacts",
  "name": "Artifact DELETE APIs",
  "keyword": "Feature",
  "line": 1
});
formatter.before({
  "duration": 406100000,
  "status": "passed"
});
formatter.scenario({
  "id": "artifact-delete-apis;attempting-to-delete-an-artifact",
  "description": "\nOnce artifacts are in DeployDB they\u0027re there forever. FOREVER.",
  "name": "Attempting to delete an artifact",
  "keyword": "Scenario",
  "line": 7,
  "type": "scenario"
});
formatter.step({
  "name": "there is an artifact",
  "keyword": "Given ",
  "line": 11
});
formatter.step({
  "name": "I DELETE \"/api/v1/artifacts/1\"",
  "keyword": "When ",
  "line": 12
});
formatter.step({
  "name": "the response should be 405",
  "keyword": "Then ",
  "line": 13
});
formatter.match({
  "location": "ArtifactSteps.groovy:8"
});
formatter.result({
  "duration": 40569000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/artifacts/1",
      "offset": 10
    }
  ],
  "location": "HttpSteps.groovy:19"
});
formatter.result({
  "duration": 24978000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "405",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:54"
});
formatter.result({
  "duration": 145000,
  "status": "passed"
});
formatter.after({
  "duration": 11105000,
  "status": "passed"
});
formatter.uri("api/artifact/reading.feature");
formatter.feature({
  "id": "artifact-read-apis",
  "description": "\nAs a RESTful client or user\nI should be able to read information about artifacts",
  "name": "Artifact READ APIs",
  "keyword": "Feature",
  "line": 1
});
formatter.before({
  "duration": 323643000,
  "status": "passed"
});
formatter.before({
  "duration": 83000,
  "status": "passed"
});
formatter.scenario({
  "id": "artifact-read-apis;fetching-an-artifact-by-id-that-exists",
  "tags": [
    {
      "name": "@freezetime",
      "line": 7
    }
  ],
  "description": "",
  "name": "Fetching an artifact by ID that exists",
  "keyword": "Scenario",
  "line": 8,
  "type": "scenario"
});
formatter.step({
  "name": "there is an artifact",
  "keyword": "Given ",
  "line": 10
});
formatter.step({
  "name": "I GET \"/api/v1/artifacts/1\"",
  "keyword": "When ",
  "line": 11
});
formatter.step({
  "name": "the response should be 200",
  "keyword": "Then ",
  "line": 12
});
formatter.step({
  "name": "the body should be JSON:",
  "keyword": "And ",
  "line": 13,
  "doc_string": {
    "value": "  {\n    \"id\" : 1,\n    \"group\" : \"com.example.cucumber\",\n    \"name\" : \"cucumber-artifact\",\n    \"version\" : \"1.0.1\",\n    \"sourceUrl\" : \"http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.1/cucumber-artifact-1.0.1.jar\",\n    \"createdAt\" : \"{{created_timestamp}}\"\n  }",
    "line": 14,
    "content_type": ""
  }
});
formatter.match({
  "location": "ArtifactSteps.groovy:8"
});
formatter.result({
  "duration": 4325000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/artifacts/1",
      "offset": 7
    }
  ],
  "location": "HttpSteps.groovy:15"
});
formatter.result({
  "duration": 48633000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:54"
});
formatter.result({
  "duration": 161000,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:62"
});
formatter.result({
  "duration": 1150000,
  "status": "passed"
});
formatter.after({
  "duration": 11703000,
  "status": "passed"
});
formatter.after({
  "duration": 38000,
  "status": "passed"
});
formatter.before({
  "duration": 312624000,
  "status": "passed"
});
formatter.scenario({
  "id": "artifact-read-apis;fetching-an-artifact-by-id-that-doesn\u0027t-exist",
  "description": "",
  "name": "Fetching an artifact by ID that doesn\u0027t exist",
  "keyword": "Scenario",
  "line": 25,
  "type": "scenario"
});
formatter.step({
  "name": "I GET \"/api/v1/artifacts/1\"",
  "keyword": "When ",
  "line": 27
});
formatter.step({
  "name": "the response should be 404",
  "keyword": "Then ",
  "line": 28
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/artifacts/1",
      "offset": 7
    }
  ],
  "location": "HttpSteps.groovy:15"
});
formatter.result({
  "duration": 34350000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "404",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:54"
});
formatter.result({
  "duration": 161000,
  "status": "passed"
});
formatter.after({
  "duration": 13189000,
  "status": "passed"
});
formatter.before({
  "duration": 325396000,
  "status": "passed"
});
formatter.scenario({
  "id": "artifact-read-apis;fetching-an-artifact-by-a-non-numeric-id",
  "tags": [
    {
      "name": "@error",
      "line": 30
    }
  ],
  "description": "",
  "name": "Fetching an artifact by a non-numeric ID",
  "keyword": "Scenario",
  "line": 31,
  "type": "scenario"
});
formatter.step({
  "name": "I GET \"/api/v1/artifacts/0xdeadbeef\"",
  "keyword": "When ",
  "line": 33
});
formatter.step({
  "name": "the response should be 400",
  "keyword": "Then ",
  "line": 34
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/artifacts/0xdeadbeef",
      "offset": 7
    }
  ],
  "location": "HttpSteps.groovy:15"
});
formatter.result({
  "duration": 30820000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "400",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:54"
});
formatter.result({
  "duration": 150000,
  "status": "passed"
});
formatter.after({
  "duration": 12734000,
  "status": "passed"
});
formatter.before({
  "duration": 256824000,
  "status": "passed"
});
formatter.before({
  "duration": 131000,
  "status": "passed"
});
formatter.scenario({
  "id": "artifact-read-apis;fetching-an-artifact-by-the-(group,-name)-tuple",
  "tags": [
    {
      "name": "@freezetime",
      "line": 37
    }
  ],
  "description": "",
  "name": "Fetching an artifact by the (group, name) tuple",
  "keyword": "Scenario",
  "line": 38,
  "type": "scenario"
});
formatter.step({
  "name": "there is an artifact",
  "keyword": "Given ",
  "line": 40
});
formatter.step({
  "name": "I GET \"/api/v1/artifacts/by-module/com.example.cucumber:cucumber-artifact\"",
  "keyword": "When ",
  "line": 41
});
formatter.step({
  "name": "the response should be 200",
  "keyword": "Then ",
  "line": 42
});
formatter.step({
  "name": "the body should be JSON:",
  "keyword": "And ",
  "line": 43,
  "doc_string": {
    "value": "  [{\n    \"id\" : 1,\n    \"createdAt\" : \"{{created_timestamp}}\",\n    \"group\" : \"com.example.cucumber\",\n    \"name\" : \"cucumber-artifact\",\n    \"version\" : \"1.0.1\",\n    \"sourceUrl\" : \"http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.1/cucumber-artifact-1.0.1.jar\"\n  }]",
    "line": 44,
    "content_type": ""
  }
});
formatter.match({
  "location": "ArtifactSteps.groovy:8"
});
formatter.result({
  "duration": 4121000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/artifacts/by-module/com.example.cucumber:cucumber-artifact",
      "offset": 7
    }
  ],
  "location": "HttpSteps.groovy:15"
});
formatter.result({
  "duration": 67956000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:54"
});
formatter.result({
  "duration": 167000,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:62"
});
formatter.result({
  "duration": 5261000,
  "status": "passed"
});
formatter.after({
  "duration": 13412000,
  "status": "passed"
});
formatter.after({
  "duration": 26000,
  "status": "passed"
});
formatter.before({
  "duration": 376520000,
  "status": "passed"
});
formatter.before({
  "duration": 65000,
  "status": "passed"
});
formatter.scenario({
  "id": "artifact-read-apis;fetching-an-artifact-by-the-(group,-name)-tuple,-pagenumber-and-perpagesize",
  "tags": [
    {
      "name": "@freezetime",
      "line": 54
    }
  ],
  "description": "",
  "name": "Fetching an artifact by the (group, name) tuple, pageNumber and perPageSize",
  "keyword": "Scenario",
  "line": 55,
  "type": "scenario"
});
formatter.step({
  "name": "there are artifacts",
  "keyword": "Given ",
  "line": 57
});
formatter.step({
  "name": "I GET \"/api/v1/artifacts/by-module/com.example.cucumber:cucumber-artifact?pageNumber\u003d0\u0026perPageSize\u003d5\"",
  "keyword": "When ",
  "line": 58
});
formatter.step({
  "name": "the response should be 200",
  "keyword": "Then ",
  "line": 59
});
formatter.step({
  "name": "the body should be JSON:",
  "keyword": "And ",
  "line": 60,
  "doc_string": {
    "value": "  [{\n    \"id\" : 2,\n    \"createdAt\" : \"{{created_timestamp}}\",\n    \"group\" : \"com.example.cucumber\",\n    \"name\" : \"cucumber-artifact\",\n    \"version\" : \"1.0.2\",\n    \"sourceUrl\" : \"http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.2/cucumber-artifact-1.0.2.jar\"\n  },\n  {\n    \"id\" : 1,\n    \"createdAt\" : \"{{created_timestamp}}\",\n    \"group\" : \"com.example.cucumber\",\n    \"name\" : \"cucumber-artifact\",\n    \"version\" : \"1.0.1\",\n    \"sourceUrl\" : \"http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.1/cucumber-artifact-1.0.1.jar\"\n  }]",
    "line": 61,
    "content_type": ""
  }
});
formatter.match({
  "location": "ArtifactSteps.groovy:15"
});
formatter.result({
  "duration": 7271000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/artifacts/by-module/com.example.cucumber:cucumber-artifact?pageNumber\u003d0\u0026perPageSize\u003d5",
      "offset": 7
    }
  ],
  "location": "HttpSteps.groovy:15"
});
formatter.result({
  "duration": 37106000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:54"
});
formatter.result({
  "duration": 130000,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:62"
});
formatter.result({
  "duration": 1830000,
  "status": "passed"
});
formatter.after({
  "duration": 15375000,
  "status": "passed"
});
formatter.after({
  "duration": 18000,
  "status": "passed"
});
formatter.before({
  "duration": 408985000,
  "status": "passed"
});
formatter.scenario({
  "id": "artifact-read-apis;fetching-an-artifact-by-the-(group,-name)-tuple-that-doesn\u0027t-exist",
  "tags": [
    {
      "name": "@error",
      "line": 79
    }
  ],
  "description": "",
  "name": "Fetching an artifact by the (group, name) tuple that doesn\u0027t exist",
  "keyword": "Scenario",
  "line": 80,
  "type": "scenario"
});
formatter.step({
  "name": "I GET \"/api/v1/artifacts/by-module/com.example.cucumber:cucumber-artifact\"",
  "keyword": "When ",
  "line": 82
});
formatter.step({
  "name": "the response should be 404",
  "keyword": "Then ",
  "line": 83
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/artifacts/by-module/com.example.cucumber:cucumber-artifact",
      "offset": 7
    }
  ],
  "location": "HttpSteps.groovy:15"
});
formatter.result({
  "duration": 31195000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "404",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:54"
});
formatter.result({
  "duration": 647000,
  "status": "passed"
});
formatter.after({
  "duration": 19528000,
  "status": "passed"
});
formatter.before({
  "duration": 320274000,
  "status": "passed"
});
formatter.scenario({
  "id": "artifact-read-apis;fetching-an-artifact-by-the-(group,-name)-tuple-with-invalid-page-number",
  "tags": [
    {
      "name": "@error",
      "line": 85
    }
  ],
  "description": "",
  "name": "Fetching an artifact by the (group, name) tuple with invalid page number",
  "keyword": "Scenario",
  "line": 86,
  "type": "scenario"
});
formatter.step({
  "name": "I GET \"/api/v1/artifacts/by-module/com.example.cucumber:cucumber-artifact?pageNumber\u003d1\u0026perPageSize\u003d5\"",
  "keyword": "When ",
  "line": 88
});
formatter.step({
  "name": "the response should be 404",
  "keyword": "Then ",
  "line": 89
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/artifacts/by-module/com.example.cucumber:cucumber-artifact?pageNumber\u003d1\u0026perPageSize\u003d5",
      "offset": 7
    }
  ],
  "location": "HttpSteps.groovy:15"
});
formatter.result({
  "duration": 26264000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "404",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:54"
});
formatter.result({
  "duration": 225000,
  "status": "passed"
});
formatter.after({
  "duration": 23103000,
  "status": "passed"
});
formatter.before({
  "duration": 399283000,
  "status": "passed"
});
formatter.scenario({
  "id": "artifact-read-apis;fetching-an-artifact-by-the-(group,-name)-tuple-with-invalid-data-type-for-pagesize",
  "tags": [
    {
      "name": "@error",
      "line": 91
    }
  ],
  "description": "",
  "name": "Fetching an artifact by the (group, name) tuple with invalid data type for pageSize",
  "keyword": "Scenario",
  "line": 92,
  "type": "scenario"
});
formatter.step({
  "name": "I GET \"/api/v1/artifacts/by-module/com.example.cucumber:cucumber-artifact?pageNumber\u003d-1\u0026perPageSize\u003d0xdeadbeef\"",
  "keyword": "When ",
  "line": 94
});
formatter.step({
  "name": "the response should be 400",
  "keyword": "Then ",
  "line": 95
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/artifacts/by-module/com.example.cucumber:cucumber-artifact?pageNumber\u003d-1\u0026perPageSize\u003d0xdeadbeef",
      "offset": 7
    }
  ],
  "location": "HttpSteps.groovy:15"
});
formatter.result({
  "duration": 27821000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "400",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:54"
});
formatter.result({
  "duration": 143000,
  "status": "passed"
});
formatter.after({
  "duration": 21567000,
  "status": "passed"
});
formatter.before({
  "duration": 266200000,
  "status": "passed"
});
formatter.before({
  "duration": 66000,
  "status": "passed"
});
formatter.scenario({
  "id": "artifact-read-apis;fetching-the-latest-artifact-by-the-(group,-name)-tuple",
  "tags": [
    {
      "name": "@freezetime",
      "line": 97
    }
  ],
  "description": "",
  "name": "Fetching the latest artifact by the (group, name) tuple",
  "keyword": "Scenario",
  "line": 98,
  "type": "scenario"
});
formatter.step({
  "name": "there is an artifact",
  "keyword": "Given ",
  "line": 100
});
formatter.step({
  "name": "I GET \"/api/v1/artifacts/by-module/com.example.cucumber:cucumber-artifact/latest\"",
  "keyword": "When ",
  "line": 101
});
formatter.step({
  "name": "the response should be 200",
  "keyword": "Then ",
  "line": 102
});
formatter.step({
  "name": "the body should be JSON:",
  "keyword": "And ",
  "line": 103,
  "doc_string": {
    "value": " {\n    \"id\" : 1,\n    \"createdAt\" : \"{{created_timestamp}}\",\n    \"group\" : \"com.example.cucumber\",\n    \"name\" : \"cucumber-artifact\",\n    \"version\" : \"1.0.1\",\n    \"sourceUrl\" : \"http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.1/cucumber-artifact-1.0.1.jar\"\n  } ",
    "line": 104,
    "content_type": ""
  }
});
formatter.match({
  "location": "ArtifactSteps.groovy:8"
});
formatter.result({
  "duration": 4177000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/artifacts/by-module/com.example.cucumber:cucumber-artifact/latest",
      "offset": 7
    }
  ],
  "location": "HttpSteps.groovy:15"
});
formatter.result({
  "duration": 32235000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:54"
});
formatter.result({
  "duration": 147000,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:62"
});
formatter.result({
  "duration": 1345000,
  "status": "passed"
});
formatter.after({
  "duration": 13002000,
  "status": "passed"
});
formatter.after({
  "duration": 26000,
  "status": "passed"
});
formatter.before({
  "duration": 337711000,
  "status": "passed"
});
formatter.before({
  "duration": 69000,
  "status": "passed"
});
formatter.scenario({
  "id": "artifact-read-apis;fetching-the-latest-artifact-by-the-(group,-name)-tuple-when-multiple-artifacts-are-present",
  "tags": [
    {
      "name": "@freezetime",
      "line": 115
    }
  ],
  "description": "",
  "name": "Fetching the latest artifact by the (group, name) tuple when multiple artifacts are present",
  "keyword": "Scenario",
  "line": 116,
  "type": "scenario"
});
formatter.step({
  "name": "there are artifacts",
  "keyword": "Given ",
  "line": 118
});
formatter.step({
  "name": "I GET \"/api/v1/artifacts/by-module/com.example.cucumber:cucumber-artifact/latest\"",
  "keyword": "When ",
  "line": 119
});
formatter.step({
  "name": "the response should be 200",
  "keyword": "Then ",
  "line": 120
});
formatter.step({
  "name": "the body should be JSON:",
  "keyword": "And ",
  "line": 121,
  "doc_string": {
    "value": " {\n    \"id\" : 2,\n    \"createdAt\" : \"{{created_timestamp}}\",\n    \"group\" : \"com.example.cucumber\",\n    \"name\" : \"cucumber-artifact\",\n    \"version\" : \"1.0.2\",\n    \"sourceUrl\" : \"http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.2/cucumber-artifact-1.0.2.jar\"\n  }",
    "line": 122,
    "content_type": ""
  }
});
formatter.match({
  "location": "ArtifactSteps.groovy:15"
});
formatter.result({
  "duration": 5497000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/artifacts/by-module/com.example.cucumber:cucumber-artifact/latest",
      "offset": 7
    }
  ],
  "location": "HttpSteps.groovy:15"
});
formatter.result({
  "duration": 27958000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:54"
});
formatter.result({
  "duration": 148000,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:62"
});
formatter.result({
  "duration": 1119000,
  "status": "passed"
});
formatter.after({
  "duration": 12618000,
  "status": "passed"
});
formatter.after({
  "duration": 25000,
  "status": "passed"
});
formatter.uri("api/artifact/updating.feature");
formatter.feature({
  "id": "artifact-update-apis",
  "description": "\nAs a RESTful client or user\nI should be *not* be able to update artifacts",
  "name": "Artifact UPDATE APIs",
  "keyword": "Feature",
  "line": 1
});
formatter.before({
  "duration": 330213000,
  "status": "passed"
});
formatter.scenario({
  "id": "artifact-update-apis;attempting-to-update-an-artifact",
  "description": "\nArtifacts must be considered immutable to ensure consistency of data from\nDeployDB. We can\u0027t different binaries with the same version, that\u0027s silly.",
  "name": "Attempting to update an artifact",
  "keyword": "Scenario",
  "line": 7,
  "type": "scenario"
});
formatter.step({
  "name": "there is an artifact",
  "keyword": "Given ",
  "line": 12
});
formatter.step({
  "name": "I PATCH \"/api/v1/artifacts/1\" with:",
  "keyword": "When ",
  "line": 13,
  "doc_string": {
    "value": "  {\n    \"version\" : \"9.0.0.0\"\n  }",
    "line": 14,
    "content_type": ""
  }
});
formatter.step({
  "name": "the response should be 405",
  "keyword": "Then ",
  "line": 19
});
formatter.match({
  "location": "ArtifactSteps.groovy:8"
});
formatter.result({
  "duration": 3692000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/artifacts/1",
      "offset": 9
    }
  ],
  "location": "HttpSteps.groovy:50"
});
formatter.result({
  "duration": 25652000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "405",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:54"
});
formatter.result({
  "duration": 137000,
  "status": "passed"
});
formatter.after({
  "duration": 12573000,
  "status": "passed"
});
formatter.uri("api/deployment/creating.feature");
formatter.feature({
  "id": "deployment-create-apis",
  "description": "\nAs a RESTful client or user\nI should be able to create new deployments in the system",
  "name": "Deployment CREATE APIs",
  "keyword": "Feature",
  "line": 1
});
formatter.before({
  "duration": 287884000,
  "status": "passed"
});
formatter.before({
  "duration": 67000,
  "status": "passed"
});
formatter.scenario({
  "id": "deployment-create-apis;creating-a-new-deployment",
  "tags": [
    {
      "name": "@freezetime",
      "line": 7
    }
  ],
  "description": "\nCreating a deployment assumes that we\u0027ve already got an Artifact stored\ninside the DeployDB database",
  "name": "Creating a new deployment",
  "keyword": "Scenario",
  "line": 8,
  "type": "scenario"
});
formatter.step({
  "name": "there is an artifact",
  "keyword": "Given ",
  "line": 13
});
formatter.step({
  "name": "I PUT to \"/api/v1/deployments\" with:",
  "keyword": "When ",
  "line": 14,
  "doc_string": {
    "value": "  {\n    \"artifactId\" : 1,\n    \"environment\" : \"pre-production\"\n  }",
    "line": 15,
    "content_type": ""
  }
});
formatter.step({
  "name": "the response should be 201",
  "keyword": "Then ",
  "line": 21
});
formatter.step({
  "name": "the body should be JSON:",
  "keyword": "And ",
  "line": 22,
  "doc_string": {
    "value": "  {\n    \"id\" : 1,\n    \"artifact\" : {\n      \"id\" : 1,\n      \"group\" : \"com.example.cucumber\",\n      \"name\" : \"cucumber-artifact\",\n      \"version\" : \"1.0.1\",\n      \"sourceUrl\" : \"http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.1/cucumber-artifact-1.0.1.jar\",\n      \"createdAt\" : \"{{created_timestamp}}\"\n    },\n    \"environment\" : \"pre-production\",\n    \"status\" : \"STARTED\",\n    \"createdAt\" : \"{{created_timestamp}}\"\n  }",
    "line": 23,
    "content_type": ""
  }
});
formatter.match({
  "location": "ArtifactSteps.groovy:8"
});
formatter.result({
  "duration": 4086000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/deployments",
      "offset": 10
    }
  ],
  "location": "HttpSteps.groovy:23"
});
formatter.result({
  "duration": 29792000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "201",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:54"
});
formatter.result({
  "duration": 91000,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:62"
});
formatter.result({
  "duration": 1057000,
  "status": "passed"
});
formatter.after({
  "duration": 25924000,
  "status": "passed"
});
formatter.after({
  "duration": 18000,
  "status": "passed"
});
formatter.uri("api/deployment/reading.feature");
formatter.feature({
  "id": "deployment-read-apis",
  "description": "\nAs a RESTful client or user\nI should be able to read information about deployments",
  "name": "Deployment READ APIs",
  "keyword": "Feature",
  "line": 1
});
formatter.before({
  "duration": 324513000,
  "status": "passed"
});
formatter.before({
  "duration": 140000,
  "status": "passed"
});
formatter.scenario({
  "id": "deployment-read-apis;fetching-a-deployment-by-id",
  "tags": [
    {
      "name": "@freezetime",
      "line": 7
    }
  ],
  "description": "",
  "name": "Fetching a deployment by ID",
  "keyword": "Scenario",
  "line": 8,
  "type": "scenario"
});
formatter.step({
  "name": "there is a deployment",
  "keyword": "Given ",
  "line": 10
});
formatter.step({
  "name": "I GET \"/api/v1/deployments/1\"",
  "keyword": "When ",
  "line": 11
});
formatter.step({
  "name": "the response should be 200",
  "keyword": "Then ",
  "line": 12
});
formatter.step({
  "name": "the body should be JSON:",
  "keyword": "And ",
  "line": 13,
  "doc_string": {
    "value": "  {\n    \"id\" : 1,\n    \"artifact\" : {\n      \"id\" : 1,\n      \"group\" : \"com.example.cucumber\",\n      \"name\" : \"cucumber-artifact\",\n      \"version\" : \"1.0.1\",\n      \"sourceUrl\" : \"http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.1/cucumber-artifact-1.0.1.jar\",\n      \"createdAt\" : \"{{created_timestamp}}\"\n    },\n    \"environment\" : \"pre-production\",\n    \"status\" : \"STARTED\",\n    \"createdAt\" : \"{{created_timestamp}}\"\n  }",
    "line": 14,
    "content_type": ""
  }
});
formatter.match({
  "location": "DeploymentSteps.groovy:8"
});
formatter.result({
  "duration": 6339000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/deployments/1",
      "offset": 7
    }
  ],
  "location": "HttpSteps.groovy:15"
});
formatter.result({
  "duration": 31641000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:54"
});
formatter.result({
  "duration": 143000,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:62"
});
formatter.result({
  "duration": 1609000,
  "status": "passed"
});
formatter.after({
  "duration": 13964000,
  "status": "passed"
});
formatter.after({
  "duration": 18000,
  "status": "passed"
});
formatter.before({
  "duration": 379924000,
  "status": "passed"
});
formatter.scenario({
  "id": "deployment-read-apis;fetching-a-deployment-by-id-that-doesn\u0027t-exist",
  "description": "",
  "name": "Fetching a deployment by ID that doesn\u0027t exist",
  "keyword": "Scenario",
  "line": 31,
  "type": "scenario"
});
formatter.step({
  "name": "I GET \"/api/v1/deployments/1\"",
  "keyword": "When ",
  "line": 33
});
formatter.step({
  "name": "the response should be 404",
  "keyword": "Then ",
  "line": 34
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/deployments/1",
      "offset": 7
    }
  ],
  "location": "HttpSteps.groovy:15"
});
formatter.result({
  "duration": 19881000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "404",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:54"
});
formatter.result({
  "duration": 131000,
  "status": "passed"
});
formatter.after({
  "duration": 10083000,
  "status": "passed"
});
formatter.uri("api/deployment/updating.feature");
formatter.feature({
  "id": "deployment-update-apis",
  "description": "\nAs a RESTful client or user\nI should be able to update existing deployments in the system",
  "name": "Deployment UPDATE APIs",
  "keyword": "Feature",
  "line": 1
});
formatter.before({
  "duration": 242085000,
  "status": "passed"
});
formatter.before({
  "duration": 72000,
  "status": "passed"
});
formatter.scenario({
  "id": "deployment-update-apis;updating-a-deployment-that-doesn\u0027t-exist",
  "tags": [
    {
      "name": "@freezetime",
      "line": 37
    },
    {
      "name": "@error",
      "line": 37
    }
  ],
  "description": "",
  "name": "Updating a deployment that doesn\u0027t exist",
  "keyword": "Scenario",
  "line": 38,
  "type": "scenario"
});
formatter.step({
  "name": "I PATCH \"/api/v1/deployments/1\" with:",
  "keyword": "When ",
  "line": 40,
  "doc_string": {
    "value": "  {\n    \"status\" : \"FAILED\"\n  }",
    "line": 41,
    "content_type": ""
  }
});
formatter.step({
  "name": "the response should be 404",
  "keyword": "Then ",
  "line": 46
});
formatter.match({
  "arguments": [
    {
      "val": "/api/v1/deployments/1",
      "offset": 9
    }
  ],
  "location": "HttpSteps.groovy:50"
});
formatter.result({
  "duration": 17291000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "404",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:54"
});
formatter.result({
  "duration": 143000,
  "status": "passed"
});
formatter.after({
  "duration": 13330000,
  "status": "passed"
});
formatter.after({
  "duration": 30000,
  "status": "passed"
});
formatter.uri("api/health.feature");
formatter.feature({
  "id": "the-health-endpoint",
  "description": "\nAs a DeployDB administrator\nI should be able to view the health of the application",
  "name": "The health endpoint",
  "keyword": "Feature",
  "line": 1
});
formatter.before({
  "duration": 296946000,
  "status": "passed"
});
formatter.scenario({
  "id": "the-health-endpoint;fetching-health-under-normal-circumstances",
  "description": "\nAssuming the application is up and running properly, the built-in\nhealthcheck endpoint should return information about all the configured\nhealthchecks currently running inside of DeployDB",
  "name": "Fetching health under normal circumstances",
  "keyword": "Scenario",
  "line": 6,
  "type": "scenario"
});
formatter.step({
  "name": "I GET \"/healthcheck\" from the admin app",
  "keyword": "When ",
  "line": 12
});
formatter.step({
  "name": "the response should be 200",
  "keyword": "Then ",
  "line": 13
});
formatter.step({
  "name": "the body should be JSON:",
  "keyword": "And ",
  "line": 14,
  "doc_string": {
    "value": "{\n  \"deadlocks\" : {\n    \"healthy\" : true\n  },\n  \"sanity\" : {\n    \"healthy\" : true\n  },\n  \"hibernate\" : {\n    \"healthy\" : true\n  },\n  \"webhook\" : {\n    \"healthy\" : true\n  }\n}",
    "line": 15,
    "content_type": ""
  }
});
formatter.match({
  "arguments": [
    {
      "val": "/healthcheck",
      "offset": 7
    }
  ],
  "location": "HttpSteps.groovy:11"
});
formatter.result({
  "duration": 48495000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:54"
});
formatter.result({
  "duration": 145000,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:62"
});
formatter.result({
  "duration": 1227000,
  "status": "passed"
});
formatter.after({
  "duration": 11692000,
  "status": "passed"
});
});