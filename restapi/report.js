$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("api/health.feature");
formatter.feature({
  "line": 1,
  "name": "The health and status endpoint",
  "description": "",
  "id": "the-health-and-status-endpoint",
  "keyword": "Feature"
});
formatter.before({
  "duration": 2286069825,
  "status": "passed"
});
formatter.scenario({
  "line": 3,
  "name": "Fetching health under normal circumstances",
  "description": "",
  "id": "the-health-and-status-endpoint;fetching-health-under-normal-circumstances",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "I GET \"/healthcheck\" from the admin app",
  "keyword": "When "
});
formatter.step({
  "line": 5,
  "name": "the response should be 200",
  "keyword": "Then "
});
formatter.step({
  "line": 6,
  "name": "the response body should be:",
  "keyword": "And ",
  "doc_string": {
    "content_type": "",
    "line": 7,
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
  "duration": 275969945,
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
  "duration": 10181800,
  "status": "passed"
});
formatter.match({
  "location": "HttpSteps.groovy:40"
});
formatter.result({
  "duration": 3496903,
  "status": "passed"
});
formatter.after({
  "duration": 20199635,
  "status": "passed"
});
});