package deploydb.cucumber

import deploydb.WebhookManager
import io.dropwizard.Application
import io.dropwizard.Configuration
import io.dropwizard.cli.ServerCommand
import io.dropwizard.db.DataSourceFactory
import io.dropwizard.flyway.FlywayFactory
import io.dropwizard.lifecycle.ServerLifecycleListener
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import io.dropwizard.testing.junit.ConfigOverride

import com.google.common.base.Strings
import com.google.common.collect.ImmutableMap
import net.sourceforge.argparse4j.inf.Namespace
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.ServerConnector
import org.flywaydb.core.Flyway
import org.hibernate.SessionFactory

import javax.annotation.Nullable
import java.util.Enumeration

import deploydb.registry.ModelRegistry

/**
 * Class for running the Dropwizard app
 *
 * Why not use DropwizardAppRule directly you might ask? Because it's an epic
 * pain to get integrated into the Before\/After hooks inside of cucumber-jvm
 */
public class StubAppRunner<C extends Configuration> {
    private final Class<? extends Application<C>> applicationClass
    private final String configPath

    private C configuration
    private Application<C> application
    private Environment environment
    private Server jettyServer
    private SessionFactory sessionFactory
    private ModelRegistry<deploydb.models.Service> serviceRegistry
    private ModelRegistry<deploydb.models.Environment> environmentRegistry
    private ModelRegistry<deploydb.models.Promotion> promotionRegistry
    private ModelRegistry<deploydb.models.pipeline.Pipeline> pipelineRegistry
    private WebhookManager webhookManager

    public StubAppRunner(Class<? extends Application<C>> applicationClass,
                        @Nullable String configPath,
                        ConfigOverride... configOverrides) {
        this.applicationClass = applicationClass
        this.configPath = configPath

        for (ConfigOverride configOverride: configOverrides) {
            configOverride.addToSystemProperties()
        }
    }

    void start() {
        startIfRequired()
    }

    void stop() {
        if (jettyServer != null) {
            jettyServer.stop()
        }
    }

    private void startIfRequired() {
        if (jettyServer != null) {
            return
        }

        try {
            application = newApplication()

            final Bootstrap<C> bootstrap = new Bootstrap<C>(application) {

                @Override
                public void run(C configuration, Environment environment) throws Exception {

                    environment.lifecycle().addServerLifecycleListener(new ServerLifecycleListener() {
                        @Override
                        public void serverStarted(Server server) {
                            jettyServer = server
                            /* Get a SessionFactory out of the application once
                             * it's up and running
                             */
                            sessionFactory = application.sessionFactory
                            webhookManager = application.webhooksManager

                            /**
                             * Get a ModelRegistry(s) from the application once it's up and running
                             */
                            serviceRegistry = application.workFlow.serviceRegistry
                            environmentRegistry = application.workFlow.environmentRegistry
                            promotionRegistry = application.workFlow.promotionRegistry
                            pipelineRegistry = application.workFlow.pipelineRegistry

                            /* We're running the DB migrations here to make sure we're running
                            * them in the same classloader environment as the DeployDB
                            * application, otherwise the Hibernate code running inside of
                            * DeployDB won't be able to "see" the in-memory DB
                            */
                            Flyway flyway = configuration.flyway.build(
                                    configuration.database.build(metricRegistry, "Flyway"));
                            flyway.clean()
                            flyway.migrate()
                        }
                    })

                    StubAppRunner.this.configuration = configuration
                    StubAppRunner.this.environment = environment
                    super.run(configuration, environment)
                }
            }

            application.initialize(bootstrap)
            final ServerCommand<C> command = new ServerCommand<>(application)

            ImmutableMap.Builder<String, Object> file = ImmutableMap.builder()

            if (!Strings.isNullOrEmpty(configPath)) {
                file.put("file", configPath)
            }
            final Namespace namespace = new Namespace(file.build())

            command.run(bootstrap, namespace)
        }
        catch (Exception e) {
            throw new RuntimeException(e)
        }
    }

    C getConfiguration() {
        return configuration
    }

    int getLocalPort() {
        return ((ServerConnector) jettyServer.getConnectors()[0]).getLocalPort()
    }

    int getAdminPort() {
        return ((ServerConnector) jettyServer.getConnectors()[1]).getLocalPort()
    }

    Application<C> newApplication() {
        try {
            return applicationClass.newInstance()
        } catch (Exception e) {
            throw new RuntimeException(e)
        }
    }

    public <A extends Application<C>> A getApplication() {
        return (A) application
    }
}
