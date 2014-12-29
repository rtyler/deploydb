package deploydb.cucumber

import deploydb.DeployDBApp
import com.sun.jersey.api.client.Client
import com.sun.jersey.api.client.ClientResponse

class AppHelper {
    private StubAppRunner runner = null
    private Client jerseyClient = null

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
