$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("api/artifact/creating.feature");
formatter.feature({
  "line": 1,
  "name": "Artifact CREATE APIs",
  "description": "\nAs a RESTful client or user\nI should be able to create new artifacts in the system",
  "id": "artifact-create-apis",
  "keyword": "Feature"
});
formatter.before({
  "duration": 5007917784,
  "status": "passed"
});
formatter.before({
  "duration": 6379772,
  "status": "passed"
});
formatter.scenario({
  "line": 8,
  "name": "Creating a new artifact",
  "description": "\nThe creation of an artifact requires group, name, version and sourceUrl.",
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
  "line": 12,
  "name": "I POST to \"/api/artifacts\" with:",
  "keyword": "When ",
  "doc_string": {
    "content_type": "",
    "line": 13,
    "value": "  {\n    \"group\" : \"com.example.cucumber\",\n    \"name\" : \"cukes\",\n    \"version\" : \"1.2.345\",\n    \"sourceUrl\" : \"http://example.com/cucumber.jar\"\n  }"
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
    "value": "  {\n    \"id\" : 1,\n    \"group\" : \"com.example.cucumber\",\n    \"name\" : \"cukes\",\n    \"version\" : \"1.2.345\",\n    \"sourceUrl\" : \"http://example.com/cucumber.jar\",\n    \"createdAt\" : \"{{created_timestamp}}\"\n  }"
  }
});
formatter.match({
  "arguments": [
    {
      "val": "/api/artifacts",
      "offset": 11
    }
  ],
  "location": "HttpSteps.groovy:23"
});
formatter.result({
  "duration": 1046550660,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "201",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:51"
});
formatter.result({
  "duration": 6856462,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:59"
});
formatter.result({
  "duration": 97992963,
  "status": "passed"
});
formatter.after({
  "duration": 54635500,
  "status": "passed"
});
formatter.after({
  "duration": 659276,
  "status": "passed"
});
formatter.before({
  "duration": 485189505,
  "status": "passed"
});
formatter.scenario({
  "line": 35,
  "name": "The request should fail when creating an Artifact without a group name",
  "description": "",
  "id": "artifact-create-apis;the-request-should-fail-when-creating-an-artifact-without-a-group-name",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 34,
      "name": "@error"
    }
  ]
});
formatter.step({
  "line": 37,
  "name": "I POST to \"/api/artifacts\" with:",
  "keyword": "When ",
  "doc_string": {
    "content_type": "",
    "line": 38,
    "value": "  {\n    \"name\" : \"cukes\",\n    \"version\" : \"1.2.345\",\n    \"sourceUrl\" : \"http://example.com/cucumber.jar\"\n  }"
  }
});
formatter.step({
  "line": 45,
  "name": "the response should be 422",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "/api/artifacts",
      "offset": 11
    }
  ],
  "location": "HttpSteps.groovy:23"
});
formatter.result({
  "duration": 87023442,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "422",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:51"
});
formatter.result({
  "duration": 159850,
  "status": "passed"
});
formatter.after({
  "duration": 33791893,
  "status": "passed"
});
formatter.before({
  "duration": 397756175,
  "status": "passed"
});
formatter.scenario({
  "line": 48,
  "name": "The request should fail when creating an Artifact without a name",
  "description": "",
  "id": "artifact-create-apis;the-request-should-fail-when-creating-an-artifact-without-a-name",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 47,
      "name": "@error"
    }
  ]
});
formatter.step({
  "line": 50,
  "name": "I POST to \"/api/artifacts\" with:",
  "keyword": "When ",
  "doc_string": {
    "content_type": "",
    "line": 51,
    "value": "  {\n    \"group\" : \"com.example.cucumber\",\n    \"version\" : \"1.2.345\",\n    \"sourceUrl\" : \"http://example.com/cucumber.jar\"\n  }"
  }
});
formatter.step({
  "line": 58,
  "name": "the response should be 422",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "/api/artifacts",
      "offset": 11
    }
  ],
  "location": "HttpSteps.groovy:23"
});
formatter.result({
  "duration": 55135983,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "422",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:51"
});
formatter.result({
  "duration": 196246,
  "status": "passed"
});
formatter.after({
  "duration": 27256851,
  "status": "passed"
});
formatter.before({
  "duration": 382371936,
  "status": "passed"
});
formatter.scenario({
  "line": 61,
  "name": "The request should fail when creating an Artifact without a version",
  "description": "",
  "id": "artifact-create-apis;the-request-should-fail-when-creating-an-artifact-without-a-version",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 60,
      "name": "@error"
    }
  ]
});
formatter.step({
  "line": 63,
  "name": "I POST to \"/api/artifacts\" with:",
  "keyword": "When ",
  "doc_string": {
    "content_type": "",
    "line": 64,
    "value": "  {\n    \"group\" : \"com.example.cucumber\",\n    \"name\" : \"cukes\",\n    \"sourceUrl\" : \"http://example.com/cucumber.jar\"\n  }"
  }
});
formatter.step({
  "line": 71,
  "name": "the response should be 422",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "/api/artifacts",
      "offset": 11
    }
  ],
  "location": "HttpSteps.groovy:23"
});
formatter.result({
  "duration": 58975607,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "422",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:51"
});
formatter.result({
  "duration": 162733,
  "status": "passed"
});
formatter.after({
  "duration": 11886542,
  "status": "passed"
});
formatter.before({
  "duration": 321890577,
  "status": "passed"
});
formatter.scenario({
  "line": 74,
  "name": "The request should fail when creating an Artifact without a sourceUrl",
  "description": "",
  "id": "artifact-create-apis;the-request-should-fail-when-creating-an-artifact-without-a-sourceurl",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 73,
      "name": "@error"
    }
  ]
});
formatter.step({
  "line": 76,
  "name": "I POST to \"/api/artifacts\" with:",
  "keyword": "When ",
  "doc_string": {
    "content_type": "",
    "line": 77,
    "value": "  {\n    \"group\" : \"com.example.cucumber\",\n    \"name\" : \"cukes\",\n    \"version\" : \"1.2.345\"\n  }"
  }
});
formatter.step({
  "line": 84,
  "name": "the response should be 422",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "/api/artifacts",
      "offset": 11
    }
  ],
  "location": "HttpSteps.groovy:23"
});
formatter.result({
  "duration": 83811717,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "422",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:51"
});
formatter.result({
  "duration": 172613,
  "status": "passed"
});
formatter.after({
  "duration": 24272673,
  "status": "passed"
});
formatter.before({
  "duration": 326562283,
  "status": "passed"
});
formatter.scenario({
  "line": 87,
  "name": "Creating an artifact with too large of a group name",
  "description": "",
  "id": "artifact-create-apis;creating-an-artifact-with-too-large-of-a-group-name",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 86,
      "name": "@error"
    }
  ]
});
formatter.step({
  "line": 89,
  "name": "I have an artifact request",
  "keyword": "Given "
});
formatter.step({
  "line": 90,
  "name": "the group is over 8192 characters",
  "keyword": "And "
});
formatter.step({
  "line": 91,
  "name": "I POST to \"/api/artifacts\"",
  "keyword": "When "
});
formatter.step({
  "line": 92,
  "name": "the response should be 422",
  "keyword": "Then "
});
formatter.match({
  "location": "HttpSteps.groovy:73"
});
formatter.result({
  "duration": 55518,
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
  "location": "HttpSteps.groovy:76"
});
formatter.result({
  "duration": 2252474,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/api/artifacts",
      "offset": 11
    }
  ],
  "location": "HttpSteps.groovy:95"
});
formatter.result({
  "duration": 87403988,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "422",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:51"
});
formatter.result({
  "duration": 175117,
  "status": "passed"
});
formatter.after({
  "duration": 79806403,
  "status": "passed"
});
formatter.before({
  "duration": 529663138,
  "status": "passed"
});
formatter.scenario({
  "line": 95,
  "name": "Creating an artifact with too large of a name",
  "description": "",
  "id": "artifact-create-apis;creating-an-artifact-with-too-large-of-a-name",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 94,
      "name": "@error"
    }
  ]
});
formatter.step({
  "line": 97,
  "name": "I have an artifact request",
  "keyword": "Given "
});
formatter.step({
  "line": 98,
  "name": "the name is over 8192 characters",
  "keyword": "And "
});
formatter.step({
  "line": 99,
  "name": "I POST to \"/api/artifacts\"",
  "keyword": "When "
});
formatter.step({
  "line": 100,
  "name": "the response should be 422",
  "keyword": "Then "
});
formatter.match({
  "location": "HttpSteps.groovy:73"
});
formatter.result({
  "duration": 36351,
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
  "location": "HttpSteps.groovy:76"
});
formatter.result({
  "duration": 973133,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/api/artifacts",
      "offset": 11
    }
  ],
  "location": "HttpSteps.groovy:95"
});
formatter.result({
  "duration": 68891591,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "422",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:51"
});
formatter.result({
  "duration": 175280,
  "status": "passed"
});
formatter.after({
  "duration": 66407156,
  "status": "passed"
});
formatter.before({
  "duration": 526963118,
  "status": "passed"
});
formatter.scenario({
  "line": 103,
  "name": "Creating an artifact with too large of a version",
  "description": "",
  "id": "artifact-create-apis;creating-an-artifact-with-too-large-of-a-version",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 102,
      "name": "@error"
    }
  ]
});
formatter.step({
  "line": 105,
  "name": "I have an artifact request",
  "keyword": "Given "
});
formatter.step({
  "line": 106,
  "name": "the version is over 255 characters",
  "keyword": "And "
});
formatter.step({
  "line": 107,
  "name": "I POST to \"/api/artifacts\"",
  "keyword": "When "
});
formatter.step({
  "line": 108,
  "name": "the response should be 422",
  "keyword": "Then "
});
formatter.match({
  "location": "HttpSteps.groovy:73"
});
formatter.result({
  "duration": 35354,
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
  "location": "HttpSteps.groovy:76"
});
formatter.result({
  "duration": 438160,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/api/artifacts",
      "offset": 11
    }
  ],
  "location": "HttpSteps.groovy:95"
});
formatter.result({
  "duration": 228189886,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "422",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:51"
});
formatter.result({
  "duration": 163344,
  "status": "passed"
});
formatter.after({
  "duration": 38588939,
  "status": "passed"
});
formatter.before({
  "duration": 306548860,
  "status": "passed"
});
formatter.scenario({
  "line": 111,
  "name": "Creating an artifact with too large of a sourceUrl",
  "description": "",
  "id": "artifact-create-apis;creating-an-artifact-with-too-large-of-a-sourceurl",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 110,
      "name": "@error"
    }
  ]
});
formatter.step({
  "line": 113,
  "name": "I have an artifact request",
  "keyword": "Given "
});
formatter.step({
  "line": 114,
  "name": "the sourceUrl is over 8192 characters",
  "keyword": "And "
});
formatter.step({
  "line": 115,
  "name": "I POST to \"/api/artifacts\"",
  "keyword": "When "
});
formatter.step({
  "line": 116,
  "name": "the response should be 422",
  "keyword": "Then "
});
formatter.match({
  "location": "HttpSteps.groovy:73"
});
formatter.result({
  "duration": 34044,
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
  "location": "HttpSteps.groovy:76"
});
formatter.result({
  "duration": 885614,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/api/artifacts",
      "offset": 11
    }
  ],
  "location": "HttpSteps.groovy:95"
});
formatter.result({
  "duration": 35947036,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "422",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:51"
});
formatter.result({
  "duration": 176767,
  "status": "passed"
});
formatter.after({
  "duration": 10266221,
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
  "duration": 223940528,
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
  "name": "I DELETE \"/api/artifacts/1\"",
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
  "duration": 49173451,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/api/artifacts/1",
      "offset": 10
    }
  ],
  "location": "HttpSteps.groovy:19"
});
formatter.result({
  "duration": 27070926,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "405",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:51"
});
formatter.result({
  "duration": 197511,
  "status": "passed"
});
formatter.after({
  "duration": 20756039,
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
  "duration": 263012945,
  "status": "passed"
});
formatter.before({
  "duration": 97516,
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
  "name": "I GET \"/api/artifacts/1\"",
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
  "duration": 4826746,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/api/artifacts/1",
      "offset": 7
    }
  ],
  "location": "HttpSteps.groovy:15"
});
formatter.result({
  "duration": 69012176,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:51"
});
formatter.result({
  "duration": 189645,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:59"
});
formatter.result({
  "duration": 2712659,
  "status": "passed"
});
formatter.after({
  "duration": 31266113,
  "status": "passed"
});
formatter.after({
  "duration": 53525,
  "status": "passed"
});
formatter.before({
  "duration": 237077678,
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
  "name": "I GET \"/api/artifacts/1\"",
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
      "val": "/api/artifacts/1",
      "offset": 7
    }
  ],
  "location": "HttpSteps.groovy:15"
});
formatter.result({
  "duration": 40020615,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "404",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:51"
});
formatter.result({
  "duration": 190317,
  "status": "passed"
});
formatter.after({
  "duration": 23395586,
  "status": "passed"
});
formatter.before({
  "duration": 262350351,
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
  "name": "I GET \"/api/artifacts/0xdeadbeef\"",
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
      "val": "/api/artifacts/0xdeadbeef",
      "offset": 7
    }
  ],
  "location": "HttpSteps.groovy:15"
});
formatter.result({
  "duration": 31066772,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "400",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:51"
});
formatter.result({
  "duration": 160923,
  "status": "passed"
});
formatter.after({
  "duration": 16238828,
  "status": "passed"
});
formatter.before({
  "duration": 280426163,
  "status": "passed"
});
formatter.before({
  "duration": 68477,
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
  "name": "I GET \"/api/artifacts/by-module/com.example.cucumber:cucumber-artifact\"",
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
    "value": "  [{\n    \"id\" : 1,\n    \"createdAt\" : \"{{created_timestamp}}\",\n    \"group\" : \"com.example.cucumber\",\n    \"name\" : \"cucumber-artifact\",\n    \"version\" : \"1.0.1\",\n    \"sourceUrl\" : \"http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.1/cucumber-artifact-1.0.1.jar\"\n  }]"
  }
});
formatter.match({
  "location": "ArtifactSteps.groovy:8"
});
formatter.result({
  "duration": 4522796,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/api/artifacts/by-module/com.example.cucumber:cucumber-artifact",
      "offset": 7
    }
  ],
  "location": "HttpSteps.groovy:15"
});
formatter.result({
  "duration": 85488476,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:51"
});
formatter.result({
  "duration": 193086,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:59"
});
formatter.result({
  "duration": 7264877,
  "status": "passed"
});
formatter.after({
  "duration": 22737414,
  "status": "passed"
});
formatter.after({
  "duration": 31323,
  "status": "passed"
});
formatter.before({
  "duration": 233651010,
  "status": "passed"
});
formatter.before({
  "duration": 72273,
  "status": "passed"
});
formatter.scenario({
  "line": 55,
  "name": "Fetching an artifact by the (group, name) tuple, pageNumber and perPageSize",
  "description": "",
  "id": "artifact-read-apis;fetching-an-artifact-by-the-(group,-name)-tuple,-pagenumber-and-perpagesize",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 54,
      "name": "@freezetime"
    }
  ]
});
formatter.step({
  "line": 57,
  "name": "there are artifacts",
  "keyword": "Given "
});
formatter.step({
  "line": 58,
  "name": "I GET \"/api/artifacts/by-module/com.example.cucumber:cucumber-artifact?pageNumber\u003d0\u0026perPageSize\u003d5\"",
  "keyword": "When "
});
formatter.step({
  "line": 59,
  "name": "the response should be 200",
  "keyword": "Then "
});
formatter.step({
  "line": 60,
  "name": "the body should be JSON:",
  "keyword": "And ",
  "doc_string": {
    "content_type": "",
    "line": 61,
    "value": "  [{\n    \"id\" : 2,\n    \"createdAt\" : \"{{created_timestamp}}\",\n    \"group\" : \"com.example.cucumber\",\n    \"name\" : \"cucumber-artifact\",\n    \"version\" : \"1.0.2\",\n    \"sourceUrl\" : \"http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.2/cucumber-artifact-1.0.2.jar\"\n  },\n  {\n    \"id\" : 1,\n    \"createdAt\" : \"{{created_timestamp}}\",\n    \"group\" : \"com.example.cucumber\",\n    \"name\" : \"cucumber-artifact\",\n    \"version\" : \"1.0.1\",\n    \"sourceUrl\" : \"http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.1/cucumber-artifact-1.0.1.jar\"\n  }]"
  }
});
formatter.match({
  "location": "ArtifactSteps.groovy:15"
});
formatter.result({
  "duration": 11965228,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/api/artifacts/by-module/com.example.cucumber:cucumber-artifact?pageNumber\u003d0\u0026perPageSize\u003d5",
      "offset": 7
    }
  ],
  "location": "HttpSteps.groovy:15"
});
formatter.result({
  "duration": 41121949,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:51"
});
formatter.result({
  "duration": 233519,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:59"
});
formatter.result({
  "duration": 2145491,
  "status": "passed"
});
formatter.after({
  "duration": 19091441,
  "status": "passed"
});
formatter.after({
  "duration": 33532,
  "status": "passed"
});
formatter.before({
  "duration": 236501995,
  "status": "passed"
});
formatter.scenario({
  "line": 80,
  "name": "Fetching an artifact by the (group, name) tuple that doesn\u0027t exist",
  "description": "",
  "id": "artifact-read-apis;fetching-an-artifact-by-the-(group,-name)-tuple-that-doesn\u0027t-exist",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 79,
      "name": "@error"
    }
  ]
});
formatter.step({
  "line": 82,
  "name": "I GET \"/api/artifacts/by-module/com.example.cucumber:cucumber-artifact\"",
  "keyword": "When "
});
formatter.step({
  "line": 83,
  "name": "the response should be 404",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "/api/artifacts/by-module/com.example.cucumber:cucumber-artifact",
      "offset": 7
    }
  ],
  "location": "HttpSteps.groovy:15"
});
formatter.result({
  "duration": 40128437,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "404",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:51"
});
formatter.result({
  "duration": 593328,
  "status": "passed"
});
formatter.after({
  "duration": 14748051,
  "status": "passed"
});
formatter.before({
  "duration": 265993784,
  "status": "passed"
});
formatter.scenario({
  "line": 86,
  "name": "Fetching an artifact by the (group, name) tuple with invalid page number",
  "description": "",
  "id": "artifact-read-apis;fetching-an-artifact-by-the-(group,-name)-tuple-with-invalid-page-number",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 85,
      "name": "@error"
    }
  ]
});
formatter.step({
  "line": 88,
  "name": "I GET \"/api/artifacts/by-module/com.example.cucumber:cucumber-artifact?pageNumber\u003d1\u0026perPageSize\u003d5\"",
  "keyword": "When "
});
formatter.step({
  "line": 89,
  "name": "the response should be 404",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "/api/artifacts/by-module/com.example.cucumber:cucumber-artifact?pageNumber\u003d1\u0026perPageSize\u003d5",
      "offset": 7
    }
  ],
  "location": "HttpSteps.groovy:15"
});
formatter.result({
  "duration": 28576623,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "404",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:51"
});
formatter.result({
  "duration": 260170,
  "status": "passed"
});
formatter.after({
  "duration": 19028912,
  "status": "passed"
});
formatter.before({
  "duration": 214671303,
  "status": "passed"
});
formatter.scenario({
  "line": 92,
  "name": "Fetching an artifact by the (group, name) tuple with invalid data type for pageSize",
  "description": "",
  "id": "artifact-read-apis;fetching-an-artifact-by-the-(group,-name)-tuple-with-invalid-data-type-for-pagesize",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 91,
      "name": "@error"
    }
  ]
});
formatter.step({
  "line": 94,
  "name": "I GET \"/api/artifacts/by-module/com.example.cucumber:cucumber-artifact?pageNumber\u003d-1\u0026perPageSize\u003d0xdeadbeef\"",
  "keyword": "When "
});
formatter.step({
  "line": 95,
  "name": "the response should be 400",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "/api/artifacts/by-module/com.example.cucumber:cucumber-artifact?pageNumber\u003d-1\u0026perPageSize\u003d0xdeadbeef",
      "offset": 7
    }
  ],
  "location": "HttpSteps.groovy:15"
});
formatter.result({
  "duration": 23312862,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "400",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:51"
});
formatter.result({
  "duration": 155560,
  "status": "passed"
});
formatter.after({
  "duration": 15529851,
  "status": "passed"
});
formatter.before({
  "duration": 274891341,
  "status": "passed"
});
formatter.before({
  "duration": 72236,
  "status": "passed"
});
formatter.scenario({
  "line": 98,
  "name": "Fetching the latest artifact by the (group, name) tuple",
  "description": "",
  "id": "artifact-read-apis;fetching-the-latest-artifact-by-the-(group,-name)-tuple",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 97,
      "name": "@freezetime"
    }
  ]
});
formatter.step({
  "line": 100,
  "name": "there is an artifact",
  "keyword": "Given "
});
formatter.step({
  "line": 101,
  "name": "I GET \"/api/artifacts/by-module/com.example.cucumber:cucumber-artifact/latest\"",
  "keyword": "When "
});
formatter.step({
  "line": 102,
  "name": "the response should be 200",
  "keyword": "Then "
});
formatter.step({
  "line": 103,
  "name": "the body should be JSON:",
  "keyword": "And ",
  "doc_string": {
    "content_type": "",
    "line": 104,
    "value": " {\n    \"id\" : 1,\n    \"createdAt\" : \"{{created_timestamp}}\",\n    \"group\" : \"com.example.cucumber\",\n    \"name\" : \"cucumber-artifact\",\n    \"version\" : \"1.0.1\",\n    \"sourceUrl\" : \"http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.1/cucumber-artifact-1.0.1.jar\"\n  } "
  }
});
formatter.match({
  "location": "ArtifactSteps.groovy:8"
});
formatter.result({
  "duration": 12915794,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/api/artifacts/by-module/com.example.cucumber:cucumber-artifact/latest",
      "offset": 7
    }
  ],
  "location": "HttpSteps.groovy:15"
});
formatter.result({
  "duration": 48646919,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:51"
});
formatter.result({
  "duration": 164081,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:59"
});
formatter.result({
  "duration": 6729897,
  "status": "passed"
});
formatter.after({
  "duration": 31186401,
  "status": "passed"
});
formatter.after({
  "duration": 31862,
  "status": "passed"
});
formatter.before({
  "duration": 350521629,
  "status": "passed"
});
formatter.before({
  "duration": 75339,
  "status": "passed"
});
formatter.scenario({
  "line": 116,
  "name": "Fetching the latest artifact by the (group, name) tuple when multiple artifacts are present",
  "description": "",
  "id": "artifact-read-apis;fetching-the-latest-artifact-by-the-(group,-name)-tuple-when-multiple-artifacts-are-present",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 115,
      "name": "@freezetime"
    }
  ]
});
formatter.step({
  "line": 118,
  "name": "there are artifacts",
  "keyword": "Given "
});
formatter.step({
  "line": 119,
  "name": "I GET \"/api/artifacts/by-module/com.example.cucumber:cucumber-artifact/latest\"",
  "keyword": "When "
});
formatter.step({
  "line": 120,
  "name": "the response should be 200",
  "keyword": "Then "
});
formatter.step({
  "line": 121,
  "name": "the body should be JSON:",
  "keyword": "And ",
  "doc_string": {
    "content_type": "",
    "line": 122,
    "value": " {\n    \"id\" : 2,\n    \"createdAt\" : \"{{created_timestamp}}\",\n    \"group\" : \"com.example.cucumber\",\n    \"name\" : \"cucumber-artifact\",\n    \"version\" : \"1.0.2\",\n    \"sourceUrl\" : \"http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.2/cucumber-artifact-1.0.2.jar\"\n  }"
  }
});
formatter.match({
  "location": "ArtifactSteps.groovy:15"
});
formatter.result({
  "duration": 7363184,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/api/artifacts/by-module/com.example.cucumber:cucumber-artifact/latest",
      "offset": 7
    }
  ],
  "location": "HttpSteps.groovy:15"
});
formatter.result({
  "duration": 31311427,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:51"
});
formatter.result({
  "duration": 153594,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:59"
});
formatter.result({
  "duration": 1491140,
  "status": "passed"
});
formatter.after({
  "duration": 23642661,
  "status": "passed"
});
formatter.after({
  "duration": 35550,
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
  "duration": 373947041,
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
  "name": "I PATCH \"/api/artifacts/1\" with:",
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
  "duration": 3275012,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/api/artifacts/1",
      "offset": 9
    }
  ],
  "location": "HttpSteps.groovy:47"
});
formatter.result({
  "duration": 25263560,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "405",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:51"
});
formatter.result({
  "duration": 180222,
  "status": "passed"
});
formatter.after({
  "duration": 8386046,
  "status": "passed"
});
formatter.uri("api/deployment/creating.feature");
formatter.feature({
  "line": 1,
  "name": "Deployment CREATE APIs",
  "description": "",
  "id": "deployment-create-apis",
  "keyword": "Feature"
});
formatter.before({
  "duration": 295248670,
  "status": "passed"
});
formatter.before({
  "duration": 75577,
  "status": "passed"
});
formatter.scenario({
  "line": 5,
  "name": "Creating a new deployment",
  "description": "\nCreating a deployment is done automatically at the beginning of a flow for\nan Artifact. There\u0027s no need for an API client to create a deployment on\ntheir own.",
  "id": "deployment-create-apis;creating-a-new-deployment",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 4,
      "name": "@freezetime"
    }
  ]
});
formatter.step({
  "line": 11,
  "name": "there is an artifact",
  "keyword": "Given "
});
formatter.step({
  "line": 12,
  "name": "I POST to \"/api/deployments\" with:",
  "keyword": "When ",
  "doc_string": {
    "content_type": "",
    "line": 13,
    "value": "  {\n    \"artifactId\" : 1,\n    \"environment\" : \"pre-production\"\n  }"
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
  "duration": 7463459,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/api/deployments",
      "offset": 11
    }
  ],
  "location": "HttpSteps.groovy:23"
});
formatter.result({
  "duration": 29609690,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "405",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:51"
});
formatter.result({
  "duration": 173426,
  "status": "passed"
});
formatter.after({
  "duration": 23297761,
  "status": "passed"
});
formatter.after({
  "duration": 34596,
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
  "duration": 277271015,
  "status": "passed"
});
formatter.before({
  "duration": 73604,
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
  "name": "I GET \"/api/deployments/1\"",
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
    "value": "  {\n    \"id\" : 1,\n    \"artifact\" : {\n      \"id\" : 1,\n      \"group\" : \"com.example.cucumber\",\n      \"name\" : \"cucumber-artifact\",\n      \"version\" : \"1.0.1\",\n      \"sourceUrl\" : \"http://example.com/maven/com.example.cucumber/cucumber-artifact/1.0.1/cucumber-artifact-1.0.1.jar\",\n      \"createdAt\" : \"{{created_timestamp}}\"\n    },\n    \"environment\" : \"pre-production\",\n    \"status\" : \"STARTED\",\n    \"createdAt\" : \"{{created_timestamp}}\"\n  }"
  }
});
formatter.match({
  "location": "DeploymentSteps.groovy:8"
});
formatter.result({
  "duration": 6175896,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/api/deployments/1",
      "offset": 7
    }
  ],
  "location": "HttpSteps.groovy:15"
});
formatter.result({
  "duration": 34043914,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:51"
});
formatter.result({
  "duration": 149717,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:59"
});
formatter.result({
  "duration": 4547639,
  "status": "passed"
});
formatter.after({
  "duration": 42468610,
  "status": "passed"
});
formatter.after({
  "duration": 32166,
  "status": "passed"
});
formatter.before({
  "duration": 249021611,
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
  "name": "I GET \"/api/deployments/1\"",
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
      "val": "/api/deployments/1",
      "offset": 7
    }
  ],
  "location": "HttpSteps.groovy:15"
});
formatter.result({
  "duration": 23652058,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "404",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:51"
});
formatter.result({
  "duration": 156203,
  "status": "passed"
});
formatter.after({
  "duration": 21789929,
  "status": "passed"
});
formatter.uri("api/deployment/updating.feature");
formatter.feature({
  "line": 1,
  "name": "Deployment UPDATE APIs",
  "description": "\nAs a RESTful client or user\nI should be able to update existing deployments in the system",
  "id": "deployment-update-apis",
  "keyword": "Feature"
});
formatter.before({
  "duration": 319717925,
  "status": "passed"
});
formatter.before({
  "duration": 71445,
  "status": "passed"
});
formatter.scenario({
  "line": 38,
  "name": "Updating a deployment that doesn\u0027t exist",
  "description": "",
  "id": "deployment-update-apis;updating-a-deployment-that-doesn\u0027t-exist",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 37,
      "name": "@freezetime"
    },
    {
      "line": 37,
      "name": "@error"
    }
  ]
});
formatter.step({
  "line": 40,
  "name": "I PATCH \"/api/deployments/1\" with:",
  "keyword": "When ",
  "doc_string": {
    "content_type": "",
    "line": 41,
    "value": "  {\n    \"status\" : \"FAILED\"\n  }"
  }
});
formatter.step({
  "line": 46,
  "name": "the response should be 404",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "/api/deployments/1",
      "offset": 9
    }
  ],
  "location": "HttpSteps.groovy:47"
});
formatter.result({
  "duration": 26707624,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "404",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:51"
});
formatter.result({
  "duration": 155980,
  "status": "passed"
});
formatter.after({
  "duration": 14490968,
  "status": "passed"
});
formatter.after({
  "duration": 33565,
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
  "duration": 476773513,
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
  "location": "HttpSteps.groovy:11"
});
formatter.result({
  "duration": 782528338,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 23
    }
  ],
  "location": "HttpSteps.groovy:51"
});
formatter.result({
  "duration": 142472,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:59"
});
formatter.result({
  "duration": 1186611,
  "status": "passed"
});
formatter.after({
  "duration": 16057380,
  "status": "passed"
});
});