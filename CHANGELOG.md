# DeployDB Changelog

## 0.2.0 - Antares

* [#3](https://github.com/lookout/deploydb/issues/3) - Model what "Promotion Criteria" looks like
* [#9](https://github.com/lookout/deploydb/issues/9) - Allow defining a Pipeline via YAML
* [#14](https://github.com/lookout/deploydb/issues/14) - DeployDB needs some persistent queueing abstraction
* [#26](https://github.com/lookout/deploydb/issues/26) - Invalid JSON in cucumber tests doesn't properly raise exceptions
* [#32](https://github.com/lookout/deploydb/issues/32) - Fix the request artifact with support for pageNumber=0 and pageSize=0
* [#35](https://github.com/lookout/deploydb/issues/35) - Administrators should be able to configure webhooks for deployment creation
* [#44](https://github.com/lookout/deploydb/issues/44) - DeployDB should be able to reload configuration with an API call
* [#77](https://github.com/lookout/deploydb/issues/77) - 201 CREATED responses should include LOCATION header of newly created resource which can be GET'd later.
* [#92](https://github.com/lookout/deploydb/issues/92) - Upgrade to Dropwizard 0.8.0 final
* [#95](https://github.com/lookout/deploydb/issues/95) - Add REST API to GET Results of a promotion associated with the deployment
* [#99](https://github.com/lookout/deploydb/issues/99) - Add restart logic and DB persistence
* [#102](https://github.com/lookout/deploydb/pull/102) - Upgrade to Dropwizard 0.8 final
* [#103](https://github.com/lookout/deploydb/issues/103) - When used with MySql on AWS, Some SQL commands failed to execute 
* [#104](https://github.com/lookout/deploydb/pull/104) - Added instructions for how to run the deployDb application
* [#106](https://github.com/lookout/deploydb/issues/106) - Webhooks should have a `type` field to differentiate event types
* [#107](https://github.com/lookout/deploydb/issues/107) - Add deploydb support for whoas configuration
* [#110](https://github.com/lookout/deploydb/issues/110) - When multiple webhooks are configured, only one webhook is invoked
* [#111](https://github.com/lookout/deploydb/issues/111) - Webhook invocation fails when environment webhook has empty event type like started
* [#113](https://github.com/lookout/deploydb/pull/113) - Fix the multiple webhooks invocation use case
* [#114](https://github.com/lookout/deploydb/pull/114) - Using flyway java(groovy) migrations for sql compatibility with diff DBs
* [#115](https://github.com/lookout/deploydb/pull/115) - Issue 111 webhook invocation fails for for empty started config
* [#116](https://github.com/lookout/deploydb/pull/116) - Upgrade to Dropwizard 0.8 final
* [#119](https://github.com/lookout/deploydb/pull/119) - Issue 106 webhook content type for events
* [#120](https://github.com/lookout/deploydb/issues/120) - Define new REST API to reload configuration with an API call
* [#121](https://github.com/lookout/deploydb/pull/121) - Defined REST API for configuration reload
* [#122](https://github.com/lookout/deploydb/pull/122) - Added configReload admin task to reload the config, if no deployments are in progress
* [#123](https://github.com/lookout/deploydb/pull/123) - Fix V2,4-6 migrations to work against MySQL
* [#124](https://github.com/lookout/deploydb/pull/124) - Remove integer type lengths in schema migrations


## 0.1.0 - Minimum Loveable Pipeline

* [#27](https://github.com/lookout/deploydb/pull/27) - Fix for issue #23
* [#28](https://github.com/lookout/deploydb/issues/28) - Decouple docs generation from test tasks
* [#29](https://github.com/lookout/deploydb/issues/29) - The request should fail when creating an Artifact without a version
* [#33](https://github.com/lookout/deploydb/issues/33) - In the create artifact request, validate max length for group, name, version & sourceUrl
* [#36](https://github.com/lookout/deploydb/issues/36) - We need some ability to deserialize YAML into Models
* [#37](https://github.com/lookout/deploydb/pull/37) - Submitting some outstanding changes I had lying around
* [#40](https://github.com/lookout/deploydb/pull/40) - Stop generating groovy and asciidoctor docs when running tests
* [#43](https://github.com/lookout/deploydb/issues/43) - Expose Services via a read API
* [#45](https://github.com/lookout/deploydb/issues/45) - How DeployDb loads yaml-based/configuration-driven models
* [#47](https://github.com/lookout/deploydb/pull/47) - Add notes covering changes to webhooks and more service configs
* [#48](https://github.com/lookout/deploydb/issues/48) - Update Deployment Model to include Service, Promotion Status for the Artifact deployed
* [#52](https://github.com/lookout/deploydb/pull/52) - Initial cut at restructuring of APIs
* [#53](https://github.com/lookout/deploydb/issues/53) - Use yaml filename as a key (id) for Service Model instance
* [#59](https://github.com/lookout/deploydb/issues/59) - Expose Environments via REST APIs
* [#61](https://github.com/lookout/deploydb/issues/61) - Expose API to return the configured Promotions
* [#62](https://github.com/lookout/deploydb/pull/62) - Add read API for promotions
* [#63](https://github.com/lookout/deploydb/pull/63) - Defined environment model, backend for REST APIs, added spock tests
* [#64](https://github.com/lookout/deploydb/issues/64) - Add failure_strategy to Service Model
* [#66](https://github.com/lookout/deploydb/issues/66) - Model how pipeline looks like
* [#68](https://github.com/lookout/deploydb/issues/68) - Add Flow model to capture the state of the artifact deployment
* [#70](https://github.com/lookout/deploydb/pull/70) - Added failure_strategy to Service Model
* [#71](https://github.com/lookout/deploydb/issues/71) - Create a webhook notification for deployment created 
* [#72](https://github.com/lookout/deploydb/issues/72) - Create a webhook notification for deployment started 
* [#73](https://github.com/lookout/deploydb/issues/73) - Create a webhook notification for deployment completed
* [#75](https://github.com/lookout/deploydb/issues/75) - Update Deployment REST APIs as discussed in DeployDb WorkFlow
* [#76](https://github.com/lookout/deploydb/pull/76) - Refactored the deployment REST API as per the WorkFlow doc
* [#82](https://github.com/lookout/deploydb/issues/82) - Add Promotion status to Deployment
* [#83](https://github.com/lookout/deploydb/issues/83) - Incorporate workflow for all the internal and external triggers
* [#90](https://github.com/lookout/deploydb/issues/90) - Integration test/sanity check 0.1 for basic functionality
* [#91](https://github.com/lookout/deploydb/issues/91) - Release 0.1.0 to Bintray

