package deploydb


import javax.ws.rs.WebApplicationException
import javax.ws.rs.core.Response


public class WorkFlow {
    private final DeployDBApp deployDBApp
    private registry.ModelRegistry<models.Promotion> promotionRegistry
    private registry.ModelRegistry<models.Environment> environmentRegistry
    private registry.ModelRegistry<models.pipeline.Pipeline> pipelineRegistry
    private registry.ModelRegistry<models.Service> serviceRegistry
    private ModelLoader<models.Promotion> promotionLoader
    private ModelLoader<models.Environment> environmentLoader
    private ModelLoader<models.pipeline.Pipeline> pipelineLoader
    private ModelLoader<models.Service> serviceLoader
    private dao.ArtifactDAO artifactDAO
    private dao.DeploymentDAO deploymentDAO
    private dao.FlowDAO flowDAO

    WorkFlow(DeployDBApp app) {
        this.deployDBApp = app
    }

    void initialize() {
        /**
         * Instantiate DAO objects
         */
        artifactDAO = new dao.ArtifactDAO(this.deployDBApp.getSessionFactory())
        deploymentDAO = new dao.DeploymentDAO(this.deployDBApp.getSessionFactory())
        flowDAO = new dao.FlowDAO(this.deployDBApp.getSessionFactory())

        /**
         * Instantiate registries for in memory storage
         */
        promotionRegistry = new registry.ModelRegistry<models.Promotion>()
        environmentRegistry = new registry.ModelRegistry<models.Environment>()
        pipelineRegistry = new registry.ModelRegistry<models.pipeline.Pipeline>()
        serviceRegistry = new registry.ModelRegistry<models.Service>()

        /**
         * Instantiate in memory loaders for yaml parsing
         */
        promotionLoader = new ModelLoader<>(models.Promotion.class)
        environmentLoader = new ModelLoader<>(models.Environment.class)
        pipelineLoader = new ModelLoader<>(models.pipeline.Pipeline.class)
        serviceLoader = new ModelLoader<>(models.Service.class)
    }

    /**
     * Work flow for artifact created
     *
     * Creates deployments and flows for the given artifact, based on the
     * information provided in the configured models
     *
     * @param artifact
     */
    void triggerArtifactCreated(models.Artifact artifact_) {

        /* Persist the Artifact */
        models.Artifact artifact = this.artifactDAO.persist(artifact_)

        /* Lookup Service */
        models.Service service = this.serviceRegistry.getAll().find() { models.Service service ->
            service.artifacts.find() { String artifactIdent ->
                artifactIdent == (artifact.group + ":" + artifact.name)
            }
        }
        if (service == null) {
            /* Artifact is left hanging */
            String serviceIdent = artifact.group + ":" + artifact.name
            throw new WebApplicationException(Response.Status.NOT_FOUND)
        }

        /* Create a flow */
        models.Flow flow = new models.Flow(artifact, service.ident)

        /* Get all pipelines */
        List<models.pipeline.Pipeline> pipelines = service.getPipelines().collect() { String pipelineIdent ->
            this.pipelineRegistry.get(pipelineIdent)
        }
        if (pipelines.isEmpty() || pipelines.contains(null)) {
            throw new WebApplicationException(Response.Status.NOT_FOUND)
        }

        /* Get all environments */
        List<deploydb.models.Environment> environments = []
        List<models.Promotion> pipePromotions = []
        pipelines.each() {
            models.pipeline.Pipeline pipeline ->
                pipeline.environments.each() {
                    String environmentIdent,
                    models.pipeline.Environment pipeEnv ->
                        environments.add(this.environmentRegistry.get(environmentIdent))
                        pipePromotions = pipeEnv.promotions.collect() {
                            String pipePromotionIdent ->
                                this.promotionRegistry.get(pipePromotionIdent)
                        }
                }
        }
        if (environments.flatten().isEmpty() || environments.flatten().contains(null)) {
            throw new WebApplicationException(Response.Status.NOT_FOUND)
        }

        /* For all environments in pipeline, create deployments */
        environments.each() { models.Environment environment ->

            /* Create deployment */
            models.Deployment deploy = new models.Deployment(artifact,
                    environment.ident, service.ident, Status.NOT_STARTED)
            if (deploy == null) {
                throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR)
            }
            if (!flow.addDeployment(deploy)) {
                throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR)
            }

            /* Get all promotions in service & pipelines. Using a Set; thus ignoring he duplicates */
            List<models.Promotion> promotions = service.getPromotions().collect() { String promotionIdent ->
                this.promotionRegistry.get(promotionIdent)
            }

            promotions.addAll(pipePromotions)
            if (promotions.isEmpty() || promotions.contains(null)) {
                throw new WebApplicationException(Response.Status.NOT_FOUND)
            }

            /* Create promotion result and add to deployment */
            promotions.unique().each() { promotion ->
                models.PromotionResult promotionResult =
                        new models.PromotionResult(promotion.ident, Status.NOT_STARTED, null)
                if (promotionResult == null) {
                    throw new WebApplicationException(Response.Status.NOT_FOUND)
                }
                if (!deploy.addPromotionResult(promotionResult)) {
                    throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR)
                }
            }
        }

        /* Persist the flow/deployment/promotionResult, with one whammy */
        this.flowDAO.persist(flow)

        /* Get deployment from the flow and start it */
        triggerDeploymentCreated(flow)
    }

    /**
     * Work flow for deployment created
     *
     * This is an internal trigger fired when deployment is verified (success/failed)
     * OR when artifact is created
     *
     * @param flow
     */
    void triggerDeploymentCreated(models.Flow flow) {

        /* Ignore if flow is marked for failed */
        if (flow.status == Status.FAILED) {
            return
        }

        /* Make Sure no deployment in progress */
        models.Deployment deploy = flow.getDeployments().find() { d ->
            d.status == Status.STARTED ||
                    d.status == Status.CREATED ||
                    d.status == Status.COMPLETED
        }
        if (deploy != null) {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR)
        }

        /* Get deployment from the flow and start it */
        deploy = flow.getDeployments().find() {
            d -> d.status == Status.NOT_STARTED
        }
        if (deploy == null) {
            /* No more deployments to deploy. All Done. Update flow status */
            if (flow.status != Status.FAILED) {
                flow.status = Status.SUCCESS
            }
            return
        }

        /* Mark Deployment as ready for deploying */
        deploy.status = Status.CREATED

        println "***MVK: triggerDeploymentCreated: deploy: ${deploy}"

        /* FIXME - Invoke deployment created webhooks */
    }

    /**
     * Work flow for deployment started
     *
     * Updates the deployment status to note a forward progress
     *
     * @param deploy
     */
    void triggerDeploymentStarted(models.Deployment deploy) {

        /* Update deployment status */
        deploy.status = Status.STARTED

        /* FIXME - Invoke deployment started webhooks */
    }

    /**
     * Work flow for deployment completed
     *
     * Updates the deployment status to note a forward progress
     *
     * @param deploy
     */
    void triggerDeploymentCompleted(models.Deployment deploy) {

        /* Update deployment status */
        deploy.status = Status.COMPLETED

        /* Update promotion results status */
        deploy.getPromotionResultSet().collect() { pr -> pr.status = Status.STARTED }

        /* FIXME - Invoke deployment completed webhooks */
    }

    /**
     * Work flow for deployment failed
     *
     * Mark the deployment and flow with failure
     *
     * @param deploy
     */
    void triggerDeploymentFailed(models.Deployment deploy) {

        /* Update deployment status */
        deploy.status = Status.FAILED

        /* Update flow status */
        deploy.getFlow().status = Status.FAILED

        /* FIXME - Invoke deployment failed webhooks */
    }

    /**
     * Work flow for promotion success
     *
     * Add promotion results to deployment. Updates deployment/flow when last
     * promotion has succeeded
     *
     * @param deploy
     */
    void triggerPromotionSuccess(models.Deployment deploy) {

        /* Find out if any other promotions are waiting for results */
        models.PromotionResult promotionResult = deploy.getPromotionResultSet().find() {
            pr -> pr.promotionIdent == Status.STARTED
        }
        if (promotionResult != null) {
            /* Wait for more promotion results */
            return
        }

        /* Update deployment status */
        if (deploy.status != Status.FAILED) {
            deploy.status = Status.SUCCESS
            /* FIXME - Invoke deployment completed webhooks */
        }

        /* Trigger NEXT deployment created for next one */
        triggerDeploymentCreated(deploy.getFlow())

    }

    /**
     * Work flow for promotion failed
     *
     * Update the deployment and flow with failed status
     *
     * @param deploy
     */
    void triggerPromotionFailed(models.Deployment deploy) {

        /* Update deployment status */
        deploy.status = Status.FAILED

        /* Update flow status */
        deploy.getFlow().status = Status.FAILED

        /* FIXME - Invoke deployment/promotion failed webhooks */
    }
}