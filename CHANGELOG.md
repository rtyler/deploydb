# DeployDB Changelog

# 0.1.0

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

