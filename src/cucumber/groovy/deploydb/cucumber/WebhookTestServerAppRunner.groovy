package deploydb.cucumber

import com.google.common.base.Strings
import com.google.common.collect.ImmutableMap
import io.dropwizard.Application
import io.dropwizard.Configuration
import io.dropwizard.cli.ServerCommand
import io.dropwizard.lifecycle.ServerLifecycleListener
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import io.dropwizard.testing.ConfigOverride
import net.sourceforge.argparse4j.inf.Namespace
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.ServerConnector
import org.flywaydb.core.Flyway
import org.hibernate.SessionFactory
import webhookTestServer.models.RequestWebhookObject
import webhookTestServer.models.ResponseWebhookObject

import javax.annotation.Nullable

/**
 *
 */
public class WebhookTestServerAppRunner<C extends Configuration> {
    private final Class<? extends Application<C>> applicationClass
    private final String configPath

    private C configuration
    private Application<C> application
    private Environment environment
    private Server jettyServer
    private RequestWebhookObject requestWebhookObject
    private ResponseWebhookObject responseWebhookObject

    public WebhookTestServerAppRunner(Class<? extends Application<C>> applicationClass,
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
                        }
                    })

                    WebhookTestServerAppRunner.this.configuration = configuration
                    WebhookTestServerAppRunner.this.environment = environment
                    super.run(configuration, environment)
                }
            }

            application.initialize(bootstrap)
            final ServerCommand<C> command = new ServerCommand<>(application)
            this.requestWebhookObject = application.requestWebhookObject
            this.responseWebhookObject = application.responseWebhookObject

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

    Environment getEnvironment() {
        return environment
    }

}
