package deploydb.cucumber

import deploydb.DeployDBApp

import com.github.mustachejava.DefaultMustacheFactory
import com.github.mustachejava.Mustache
import com.github.mustachejava.MustacheFactory

import org.glassfish.jersey.client.ClientConfig
import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider
import javax.ws.rs.core.Response
import javax.ws.rs.client.Entity

import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.Transaction
import org.hibernate.context.internal.ManagedSessionContext

import deploydb.registry.ModelRegistry
import deploydb.models.Service

class AppHelper {
    private StubAppRunner runner = null
    private Client jerseyClient = null


    SessionFactory getSessionFactory() {
        return this.runner.sessionFactory
    }

    /**
     *  Execute the {@link Closure} with a properly set up
     *  {@link org.hibernate.Session}
     *
     * @param c (required) Closure to execute with a session
     */
    void withSession(Closure c) {
        final Session session = sessionFactory.openSession()
        try {
            ManagedSessionContext.bind(session)
            c.call()
        }
        finally {
            session.close()
        }
    }

    /**
     *  Execute the {@link Closure} with a proper Service Registry
     *
     * @param c (required) Closure to execute 
     */
    void withServiceRegistry(Closure c) {
        c.call(this.runner.serviceRegistry)
    }

    String processTemplate(String buffer, Map scope) {
        DefaultMustacheFactory mf = new DefaultMustacheFactory()
        StringWriter writer = new StringWriter()
        Mustache m = mf.compile(new StringReader(buffer),
                                'cuke-stash-compiler')
        m.execute(writer, scope)

        return writer.toString()
    }

    Client getClient() {
        if (this.jerseyClient == null) {
            ClientConfig clientConfig = new ClientConfig()
            clientConfig.connectorProvider(new ApacheConnectorProvider())
            this.jerseyClient = ClientBuilder.newClient(clientConfig)
        }
        return this.jerseyClient
    }

    Response makeRequestToPath(String path, String method, Entity entity) {
        return this.makeRequestToPath(path, method, entity, false)
    }

    Response makeRequestToPath(String path, String method, Entity entity, Boolean isAdmin) {
        int port = isAdmin ? runner.adminPort : runner.localPort
        String url = String.format("http://localhost:%d${path}", port)

        return client.target(url)
                     .request()
                     .build(method, entity)
                     .invoke()
    }

    /**
     * Execute a PUT to the test server for step definitions
     */
    Response putJsonToPath(String path, String requestBody) {
        return this.makeRequestToPath(path, 'PUT', Entity.json(requestBody))
    }

    /**
     * Execute a PATCH to the test server for step definitions
     */
    Response patchJsonToPath(String path, String requestBody) {
        String mediaType = 'application/json-patch'
        return this.makeRequestToPath(path, 'PATCH', Entity.entity(requestBody, mediaType))
    }

    Response deleteFromPath(String path) {
        return this.makeRequestToPath(path, 'DELETE', null)
    }

    /**
     * Minor convenience method to make sure we're dispatching GET requests to the
     * right port in our test application
     */
    Response getFromPath(String path, boolean isAdmin) {
        return this.makeRequestToPath(path, 'GET', null , isAdmin)
    }

    void startAppWithConfiguration(String config) {
        if (this.runner != null) {
            return
        }
        this.runner = new StubAppRunner(DeployDBApp.class, config)
        this.runner.start()
    }


    void stopApp() {
        if (this.runner != null) {
            this.runner.stop()
        }
    }
}
