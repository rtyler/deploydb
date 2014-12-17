# #DeployDB

**Who, what, where and when.**

DeployDB is a tool to provide a single source of truth for artifact-based
deployments through multiple environments. It is intended to fit within an
existing infrastructure where CI and deployment orchestration are already
provided by other tools (e.g. Jenkins, Rundeck).


## Terminology

 * **Artifact**: a singular versioned file which represents an
   iteration of code to be deployed.
 * **Deployment**: taking an artifact, or artifacts, and
   delivering and executing them inside of an environment.
 * **Environment**: a collection of applications and configurations for executing
   services together across a cluster of machines.
 * **Pipeline**: a set of deployments and promotions defining the relationships
   between each environment
 * **Promotion**: a set of criteria for determining whether a deployment is
   suitable to progress to the next environment in the pipeline

## Goals/Requirements

 * Provide a single source of truth for:
   * which artifacts have been deployed to which environments
   * the success of a deployment, between the act of deployment and the
     follow-on validation of the artifacts deployed
 * Provide a centralized synchronization point for coordinating deployments
   across environments as defined by a pipeline.
 * *Not* provide redundant functionality already provided by orchestration
   tools such as Rundeck or Jenkins.
 * Intergrate with existing orchestration tools to drive deployments to
   a particular environment
 * Integration points for external tools for:
   * providing verification data around a specific deployment (e.g.
     "Deployment X was verified by Jenkins job <jobname~ build #5")
   * providing deployment start/finish information for environments
   * providing read access to deployment, artifact and environment information
     contained with DeployDB
 * Integration points should be exposed via a command line, Ops-friendly,
   interface
 * Configuration should not live in the data store and be easily driven by
   configuration management tools (e.g. Chef, Puppet)



## Use-cases/questions

  * How do we set up a new application/service in DeployDB?
  * How does promotion of one application through an environment work so that
    the entire environment doesn't have to be promoted together
  * Deployment failures should have one of three outcomes:
    * Deploy the last known good Deployment to the environment
    * Hit these webhooks (e.g. pagerduty)
    * Do nothing/record and ignore it



## An awful diagram

                                        2) publish                                            
                            +----------+   artifact +----------+                              
          +---------------> | TravisCI +------------+  Bintray |                              
          |                 +----+-----+            +-----+----+                              
          |                      |                        |                                   
          | 1) webhook           |                        |    3) webhook                     
          |  (run tests)         |                        | (artifact available)              
          |                      |                        |                                   
          |                      |                        |                                   
    +-----+----+                 |                 +------+------+                +----------+
    |  GitHub  |                 +---------------> |  #DeployDB  +--------------> |  Heroku  |
    +----------+                                   +-------------+                +----------+
                   5) report deploy/verification                4) webhook                  
                       success                                 (deploy artifact               
                                                                to environment)               
