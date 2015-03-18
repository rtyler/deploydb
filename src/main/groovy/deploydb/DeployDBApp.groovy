package deploydb

import com.google.common.collect.ImmutableList
import io.dropwizard.Application
import io.dropwizard.assets.AssetsBundle
import io.dropwizard.db.DataSourceFactory
import io.dropwizard.flyway.FlywayBundle
import io.dropwizard.flyway.FlywayFactory
import io.dropwizard.hibernate.HibernateBundle
import io.dropwizard.hibernate.SessionFactoryFactory
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import io.dropwizard.views.ViewBundle
import org.hibernate.SessionFactory
import org.joda.time.DateTimeZone
import org.slf4j.Logger
import org.slf4j.LoggerFactory


class DeployDBApp extends Application<DeployDBConfiguration> {
    private final ImmutableList models = ImmutableList.of(
            models.Artifact, models.Deployment,
            models.PromotionResult, models.Flow)
    private static final Logger logger = LoggerFactory.getLogger(DeployDBApp.class)
    private WebhookManager webhooksManager
    private WorkFlow workFlow
    private provider.V1TypeProvider typeProvider

    static void main(String[] args) throws Exception {
        new DeployDBApp().run(args)
    }

    @Override
    String getName() {
        return 'deploydb'
    }

    /** Return the current org.hibernate.SessionFactory
     *
     * This is really only ever intended to be used in integration tests as a
     * means of getting a hold of the same SessionFactory that Resources will
     * be using
     */
    SessionFactory getSessionFactory() {
        return hibernate.sessionFactory
    }

    private final HibernateBundle<DeployDBConfiguration> hibernate =
            new HibernateBundle<DeployDBConfiguration>(models, new SessionFactoryFactory()) {
                @Override
                DataSourceFactory getDataSourceFactory(DeployDBConfiguration config) {
                    return config.getDataSourceFactory()
                }
            }


    @Override
    public void initialize(Bootstrap<DeployDBConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle())
        bootstrap.addBundle(new ViewBundle())
        bootstrap.addBundle(hibernate)
        workFlow = new WorkFlow(this)

        /*
         * Force our default timezone to always be UTC
         */
        DateTimeZone.setDefault(DateTimeZone.UTC)
        logger.info("Set default timezone to UTC")

        bootstrap.addBundle(new FlywayBundle<DeployDBConfiguration>() {
            @Override
            DataSourceFactory getDataSourceFactory(DeployDBConfiguration config) {
                return config.getDataSourceFactory()
            }

            @Override
            FlywayFactory getFlywayFactory(DeployDBConfiguration config) {
                return config.getFlywayFactory()
            }
        })

        typeProvider = new provider.V1TypeProvider(bootstrap.objectMapper,
                bootstrap.validatorFactory.validator)
    }

    @Override
    public void run(DeployDBConfiguration configuration,
                    Environment environment) {

        /*
         * Create webhook manager based on configuration
         */
        webhooksManager = new WebhookManager(configuration)

         /**
         * Initialize the workflow object
         */
        workFlow.initializeDao()
        workFlow.initializeRegistry()

        /**
         * Load configuration models
         */
        File baseConfigDirectory = new File(configuration.configDirectory);
        if (baseConfigDirectory.exists() && baseConfigDirectory.isDirectory()) {
            logger.info("Loading models from directory: ${baseConfigDirectory.getCanonicalPath()}")
            try {
                workFlow.loadConfigModels(configuration.configDirectory)
            } catch (IllegalArgumentException e) {
                logger.error("failed to load models from directory: " +
                        "${baseConfigDirectory.getCanonicalPath()}, error: ${e.toString()}")
            }
        } else {
            logger.error("No DeployDB configuration found. DeployDB would not function properly")
        }

        /**
         * webhooksManager
         */
        environment.lifecycle().manage(webhooksManager)

        /**
         * Healthchecks
         */
        environment.healthChecks().register('sanity', new health.SanityHealthCheck())
        environment.healthChecks().register('webhook', new health.WebhookHealthCheck(webhooksManager))

        /**
         * Instantiate Resources classes for Jersey handlers
         */
        environment.jersey().register(typeProvider)
        environment.jersey().register(new resources.RootResource())
        environment.jersey().register(new resources.ArtifactResource(workFlow))
        environment.jersey().register(new resources.DeploymentResource(workFlow))
        environment.jersey().register(new resources.FlowResource(workFlow))
        environment.jersey().register(new resources.PromotionResource(workFlow.promotionRegistry))
        environment.jersey().register(new resources.EnvironmentResource(workFlow.environmentRegistry))
        environment.jersey().register(new resources.PipelineResource(workFlow.pipelineRegistry))
        environment.jersey().register(new resources.ServiceResource(workFlow.serviceRegistry))
    }
}
