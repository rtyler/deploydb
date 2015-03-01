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

import deploydb.registry.ModelRegistry

class DeployDBApp extends Application<DeployDBConfiguration> {
    private final ImmutableList models = ImmutableList.of(models.Artifact, models.Deployment, models.PromotionResult)
    private final Logger logger = LoggerFactory.getLogger(DeployDBApp.class)
    private WebhookManager webhooks
    private ModelRegistry<models.Service> serviceRegistry
    private ModelLoader<models.Service> serviceLoader
    private ModelRegistry<models.Environment> environmentRegistry
    private ModelLoader<models.Environment> environmentLoader
    private ModelRegistry<models.Promotion> promotionRegistry
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
        webhooks = new WebhookManager()

        /*
         * Force our default timezone to always be UTC
         */
        DateTimeZone.setDefault(DateTimeZone.UTC)
        logger.debug("Set default timezone to UTC")

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
        /**
         * Instantiate DAO objects
         */
        final dao.ArtifactDAO adao = new dao.ArtifactDAO(hibernate.sessionFactory)
        final dao.DeploymentDAO ddao = new dao.DeploymentDAO(hibernate.sessionFactory)

        /**
         * Instantiate registries for in memory storage
         */
        environmentRegistry = new ModelRegistry<>()
        serviceRegistry = new ModelRegistry<>()
        promotionRegistry = new ModelRegistry<>()

        /**
         * Instantiate in memory loaders for yaml parsing
         */
        serviceLoader = new ModelLoader<>(models.Service.class)
        environmentLoader = new ModelLoader<>(models.Environment.class)

        /**
         * Webhooks
         */
        environment.lifecycle().manage(webhooks)

        /**
         * Healthchecks
         */
        environment.healthChecks().register('sanity', new health.SanityHealthCheck())
        environment.healthChecks().register('webhook', new health.WebhookHealthCheck(webhooks))

        /**
         * Instantiate Resources classes for Jersey handlers
         */
        environment.jersey().register(typeProvider)
        environment.jersey().register(new resources.RootResource())
        environment.jersey().register(new resources.ArtifactResource(adao))
        environment.jersey().register(new resources.DeploymentResource(ddao))
        environment.jersey().register(new resources.ServiceResource(serviceRegistry))
        environment.jersey().register(new resources.EnvironmentResource(environmentRegistry))
        environment.jersey().register(new resources.PromotionResource(promotionRegistry))
    }
}
