package deploydb

import deploydb.models.PromotionResult
import groovy.io.FileType
import javax.ws.rs.WebApplicationException
import javax.ws.rs.core.Response
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/* Define a new exception to break out of loop */
class BreakLoopException extends Exception{}

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
    private ModelLoader<models.Webhook.Webhook> webhookLoader
    private dao.ArtifactDAO artifactDAO
    private dao.DeploymentDAO deploymentDAO
    private dao.FlowDAO flowDAO
    private static final Logger logger = LoggerFactory.getLogger(WorkFlow.class)

    WorkFlow(DeployDBApp app) {
        this.deployDBApp = app
    }

    void initializeDao() {
        /**
         * Instantiate DAO objects
         */
        artifactDAO = new dao.ArtifactDAO(this.deployDBApp.getSessionFactory())
        deploymentDAO = new dao.DeploymentDAO(this.deployDBApp.getSessionFactory())
        flowDAO = new dao.FlowDAO(this.deployDBApp.getSessionFactory())
    }

    void initializeRegistry() {

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
        webhookLoader = new ModelLoader<>(models.Webhook.Webhook.class)
    }

    void loadConfigModelsCommon(String modelDirName, Closure c) {
        File modelDirectory = new File(modelDirName);
        if (modelDirectory.exists() && modelDirectory.isDirectory()) {
            logger.debug("Loading model from directory: ${modelDirectory.getCanonicalPath()}");

            /* Skip everything but yaml file */
            modelDirectory.eachFileMatch(FileType.FILES, ~/^.*?\.yml/) { File modelFile ->
                try {
                    c.call(modelFile)
                } catch (BreakLoopException e) {
                    throw e
                } catch (IllegalArgumentException e) {
                    throw e /* Throw the exception again */
                } catch (all) {
                    logger.info("Failed to load model from ${modelFile.name}")
                }
            }
        }
    }

    /**
     * Read configuration into each model
     *
     * Throws an exception if dependency between models fails
     *
     * @param baseConfigDirName
     */
    void loadConfigModels(String baseConfigDirName) {

        /**
         * Instantiate new registries for in memory storage. We will overwrite the
         * older registries in the end
         */
        registry.ModelRegistry<models.Promotion> tmpPromotionRegistry =
                new registry.ModelRegistry<models.Promotion>()
        registry.ModelRegistry<models.Environment> tmpEnvironmentRegistry =
                new registry.ModelRegistry<models.Environment>()
        registry.ModelRegistry<models.pipeline.Pipeline> tmpPipelineRegistry =
                new registry.ModelRegistry<models.pipeline.Pipeline>()
        registry.ModelRegistry<models.Service> tmpServiceRegistry =
                new registry.ModelRegistry<models.Service>()
        models.Webhook.Webhook tmpWebhook = null

        /* Load promotions */
        String promotionsDirName = baseConfigDirName + "/promotions"
        loadConfigModelsCommon(promotionsDirName) { File modelFile ->
            models.Promotion promotion = this.promotionLoader.load(modelFile)
            promotion.ident = this.promotionLoader.getIdent(modelFile.name)
            tmpPromotionRegistry.put(promotion.ident, promotion)
            logger.debug("Loaded promotions model: ${promotion.ident}")
        }

        /* Load environments */
        String environmentsDirName = baseConfigDirName + "/environments"
        loadConfigModelsCommon(environmentsDirName) { File modelFile ->
            models.Environment environment = this.environmentLoader.load(modelFile)
            environment.ident = this.environmentLoader.getIdent(modelFile.name)
            tmpEnvironmentRegistry.put(environment.ident, environment)
            logger.debug("Loaded environments model: ${environment.ident}")
        }

        /* Load pipelines */
        String pipelinesDirName = baseConfigDirName + "/pipelines"
        loadConfigModelsCommon(pipelinesDirName) { File modelFile ->
            models.pipeline.Pipeline pipeline = this.pipelineLoader.load(modelFile)
            pipeline.ident = this.pipelineLoader.getIdent(modelFile.name)

            /* Validate */
            pipeline.environments.each() {
                String environmentIdent,
                models.pipeline.Environment pipelineEnvironment ->
                    /* Make sure that environments in the Pipeline are configured */
                    if (tmpEnvironmentRegistry.get(environmentIdent) == null) {
                        throw new IllegalArgumentException(
                                "Missing Environment ${environmentIdent} in Pipeline ${pipeline.ident}")
                    }
                    /* Make sure that promotions in the Pipeline are configured */
                    pipelineEnvironment.promotions.each() {
                        String pipePromotionIdent ->
                            if (tmpPromotionRegistry.get(pipePromotionIdent) == null) {
                                throw new IllegalArgumentException(
                                        "Missing Promotion ${pipePromotionIdent} in Pipeline ${pipeline.ident}")
                            }
                    }
            }

            /* Add to registry */
            tmpPipelineRegistry.put(pipeline.ident, pipeline)
            logger.debug("Loaded pipelines model: ${pipeline.ident}")
        }

        /* Load services */
        String servicesDirName = baseConfigDirName + "/services"
        loadConfigModelsCommon(servicesDirName) { File modelFile ->
            models.Service service = this.serviceLoader.load(modelFile)
            service.ident = this.serviceLoader.getIdent(modelFile.name)

            /* Validate */
            service.pipelines.each() { String pipelineIdent ->
                /* Make sure that pipelines in the Service are configured */
                if (tmpPipelineRegistry.get(pipelineIdent) == null) {
                    throw new IllegalArgumentException(
                            "Missing pipeline ${pipelineIdent} in Service ${service.ident}")
                }
            }
            service.promotions.each() { String promotionIdent ->
                /* Make sure that promotions in the Service are configured */
                if (tmpPromotionRegistry.get(promotionIdent) == null) {
                    throw new IllegalArgumentException(
                            "Missing promotion ${promotionIdent} in Service ${service.ident}")
                }
            }

            /* Add to registry */
            tmpServiceRegistry.put(service.ident, service)
            logger.debug("Loaded services model: ${service.ident}")
        }

        /* At least one service MUST be configured for deployDb to function properly */
        if (tmpServiceRegistry.getAll().isEmpty()) {
            logger.info("NO SERVICES ARE CONFIGURED. DeployDB would not function properly")
        }

        /* Load webhook */
        String webhookDirName = baseConfigDirName + "/webhook"
        try {
            loadConfigModelsCommon(webhookDirName) { File modelFile ->

                tmpWebhook = this.webhookLoader.load(modelFile)
                logger.debug("Loaded webhooks model from: ${modelFile.name}")

                /* Now that we have found a valid webhook, we are done */
                throw new BreakLoopException()
            }
        } catch (BreakLoopException e) {
            /* Nothing to do here */
        }

        /**
         * Overwrite the model registries with new ones
         */
        promotionRegistry = tmpPromotionRegistry
        environmentRegistry = tmpEnvironmentRegistry
        pipelineRegistry = tmpPipelineRegistry
        serviceRegistry = tmpServiceRegistry
        deployDBApp.webhooksManager.webhook = tmpWebhook
    }

    /**
     * Work flow for artifact created
     *
     * Creates deployments and flows for the given artifact, based on the
     * information provided in the configured models
     *
     * @param artifact
     */
    void triggerArtifactCreated(models.Artifact artifact) {

        /* Lookup Service(s) */
        List<models.Service> services = this.serviceRegistry.getAll().findAll() { models.Service service ->
            service.artifacts.find() { String artifactIdent ->
                artifactIdent == (artifact.group + ":" + artifact.name)
            }
        }
        if (services.flatten().isEmpty() || services.flatten().contains(null)) {
            String artifactIdent =
            logger.info("Failed to find Service for Artifact: " +
                         artifact.group + ":" + artifact.name +
                        ", aborting artifact deployment")
            throw new WebApplicationException(Response.Status.NOT_FOUND)
        }

        /* For each service, create a separate flow and the following */
        services.flatten().each() { models.Service service ->

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
            List<models.pipeline.Pipeline> pipelinePromotions = []
            pipelines.each() {
                models.pipeline.Pipeline pipeline ->
                    pipeline.environments.each() {
                        String environmentIdent,
                        models.pipeline.Environment pipelineEnvironment ->
                            environments.add(this.environmentRegistry.get(environmentIdent))
                            pipelinePromotions = pipelineEnvironment.promotions.collect() {
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
                models.Deployment deployment = new models.Deployment(artifact,
                        environment.ident, service.ident, Status.NOT_STARTED)
                if (deployment == null) {
                    throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR)
                }
                if (!flow.addDeployment(deployment)) {
                    throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR)
                }

                /* Get all promotions in service & pipelines */
                List<models.Promotion> promotions = service.getPromotions().collect() { String promotionIdent ->
                    this.promotionRegistry.get(promotionIdent)
                }
                promotions.addAll(pipelinePromotions)
                if (promotions.isEmpty() || promotions.contains(null)) {
                    throw new WebApplicationException(Response.Status.NOT_FOUND)
                }

                /* Create promotion result and add to deployment. Using unique() to ignore duplicates */
                promotions.unique().each() { promotion ->
                    models.PromotionResult promotionResult =
                            new models.PromotionResult(promotion.ident, Status.NOT_STARTED, null)
                    if (promotionResult == null) {
                        throw new WebApplicationException(Response.Status.NOT_FOUND)
                    }
                    if (!deployment.addPromotionResult(promotionResult)) {
                        throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR)
                    }
                }
            }

            /* Persist the artifact and flow/deployment/promotionResult, with one whammy */
            this.artifactDAO.persist(artifact)
            this.flowDAO.persist(flow)

            /* Get deployment from the flow and start it */
            triggerDeploymentCreated(flow)
        }
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
        models.Deployment deployment = flow.getDeployments().find() { d ->
            d.status == Status.STARTED ||
                    d.status == Status.CREATED ||
                    d.status == Status.COMPLETED
        }
        if (deployment != null) {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR)
        }

        /* Get deployment from the flow and start it */
        deployment = flow.getDeployments().find() {
            d -> d.status == Status.NOT_STARTED
        }
        if (deployment == null) {
            /* No more deployments to deployment. All Done. Update flow status */
            if (flow.status != Status.FAILED) {
                flow.status = Status.SUCCESS
            }
            return
        }

        /* Mark Deployment as ready for deploying */
        deployment.status = Status.CREATED

        /*
         * Create the webhook mapper for deployment
         */
        mappers.DeploymentWebhookMapper deploymentWebhookMapper = new mappers.DeploymentWebhookMapper(deployment)

        /*
         * Get the environment based webhooks for this deployment
         */
        models.Webhook.Webhook environmentWebhook =
                this.environmentRegistry.get(deployment.environmentIdent).webhooks

        /*
         * Use webhook manager to send the webhook
         */
        if (deployDBApp.webhooksManager.sendDeploymentWebhook("created", environmentWebhook,
                deploymentWebhookMapper) == false) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST)
        }

    }

    /**
     * Work flow for deployment started
     *
     * Updates the deployment status to note a forward progress
     *
     * @param deployment
     */
    void triggerDeploymentStarted(models.Deployment deployment) {

        /* Update deployment status */
        deployment.status = Status.STARTED

        /*
         * Create the webhook mapper for deployment
         */
        mappers.DeploymentWebhookMapper deploymentWebhookMapper =
                new mappers.DeploymentWebhookMapper(deployment)

        /*
         * Get the environment based webhooks for this deployment
         */
        models.Webhook.Webhook environmentWebhook = this.environmentRegistry.get(deployment.environmentIdent)?
                this.environmentRegistry.get(deployment.environmentIdent).webhooks : null

        /*
         * Use webhook manager to send the webhook
         */
        if (deployDBApp.webhooksManager.sendDeploymentWebhook("started", environmentWebhook,
                deploymentWebhookMapper) == false) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST)
        }

    }

    /**
     * Work flow for deployment completed
     *
     * Updates the deployment status to note a forward progress
     *
     * @param deployment
     */
    void triggerDeploymentCompleted(models.Deployment deployment) {

        /* Update deployment status */
        deployment.status = Status.COMPLETED

        /* Update promotion results status */
        deployment.getPromotionResultSet().collect() { pr -> pr.status = Status.STARTED }

        /*
         * Create the webhook mapper for deployment
         */
        mappers.DeploymentWebhookMapper deploymentWebhookMapper =
                new mappers.DeploymentWebhookMapper(deployment)

        /*
         * Get the environment based webhooks for this deployment
         */
        models.Webhook.Webhook environmentWebhook = this.environmentRegistry.get(deployment.environmentIdent)?
                this.environmentRegistry.get(deployment.environmentIdent).webhooks : null

        /*
         * Use webhook manager to send the webhook
         */
        if (deployDBApp.webhooksManager.sendDeploymentWebhook("completed", environmentWebhook,
                deploymentWebhookMapper) == false) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST)
        }
    }

    /**
     * Work flow for deployment failed
     *
     * Mark the deployment and flow with failure
     *
     * @param deployment
     */
    void triggerDeploymentFailed(models.Deployment deployment) {

        /* Update deployment status */
        deployment.status = Status.FAILED

        /* Update flow status */
        deployment.getFlow().status = Status.FAILED

        /* FIXME - Invoke deployment failed webhooks */
    }

    /**
     * Work flow for promotion success
     *
     * Add promotion results to deployment. Updates deployment/flow when last
     * promotion has succeeded
     *
     * @param deployment
     */
    void triggerPromotionSuccess(models.Deployment deployment, models.PromotionResult promotionResult) {

        /*
         * Create the webhook mapper for deployment
        */
        mappers.DeploymentPromotionWebhookMapper deploymentPromotionWebhookMapper =
                new mappers.DeploymentPromotionWebhookMapper(deployment, promotionResult)

        /*
         * Get the environment based webhooks for this deployment
         */
        models.Webhook.Webhook environmentWebhook = this.environmentRegistry.get(deployment.environmentIdent)?
                this.environmentRegistry.get(deployment.environmentIdent).webhooks : null

        /*
         * Use webhook manager to send the webhook
         */
        if (deployDBApp.webhooksManager.sendPromotionWebhook("completed", environmentWebhook,
                deploymentPromotionWebhookMapper) == false) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST)
        }


        /* Find out if any other promotions are waiting for results */
        models.PromotionResult startedPromotionResult = deployment.getPromotionResultSet().find() {
            pr -> pr.promotionIdent == Status.STARTED
        }
        if (startedPromotionResult != null) {
            /* Wait for more promotion results */
            return
        }

        /* Update deployment status */
        if (deployment.status != Status.FAILED) {
            deployment.status = Status.SUCCESS
            /* FIXME - Invoke deployment verified webhooks */
        }

        /* Trigger NEXT deployment created for next one */
        triggerDeploymentCreated(deployment.getFlow())

    }

    /**
     * Work flow for promotion failed
     *
     * Update the deployment and flow with failed status
     *
     * @param deployment
     */
    void triggerPromotionFailed(models.Deployment deployment, PromotionResult promotionResult) {

        /*
         * Create the webhook mapper for deployment
        */
        mappers.DeploymentPromotionWebhookMapper deploymentPromotionWebhookMapper =
                new mappers.DeploymentPromotionWebhookMapper(deployment, promotionResult)

        /*
         * Get the environment based webhooks for this deployment
         */
        models.Webhook.Webhook environmentWebhook = this.environmentRegistry.get(deployment.environmentIdent)?
                this.environmentRegistry.get(deployment.environmentIdent).webhooks : null

        /*
         * Use webhook manager to send the webhook
         */
        if (deployDBApp.webhooksManager.sendPromotionWebhook("completed", environmentWebhook,
                deploymentPromotionWebhookMapper) == false) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST)
        }

        /* Update deployment status */
        deployment.status = Status.FAILED

        /* Update flow status */
        deployment.getFlow().status = Status.FAILED

        /* FIXME - Invoke deployment verified(failed) webhooks */
    }
}