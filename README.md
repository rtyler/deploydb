# #DeployDB

**Who, what, where and when.**

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

## Goals

