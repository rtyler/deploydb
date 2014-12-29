package deploydb.cucumber

import deploydb.DeployDBApp
import com.github.mustachejava.DefaultMustacheFactory
import com.github.mustachejava.Mustache
import com.github.mustachejava.MustacheFactory
import com.sun.jersey.api.client.Client
import com.sun.jersey.api.client.ClientResponse
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.Transaction
import org.hibernate.context.internal.ManagedSessionContext

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
            this.jerseyClient = new Client()
        }
        return this.jerseyClient
    }

    /**
    * Minor convenience method to make sure we're dispatching GET requests to the
    * right port in our test application
    */
    ClientResponse getFromPath(String path, boolean isAdmin) {
        int port = isAdmin ? runner.getAdminPort() : runner.getLocalPort()

        return client.resource(
                        String.format("http://localhost:%d${path}", port))
                     .get(ClientResponse.class)
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
