package deploydb


import io.dropwizard.Application
import io.dropwizard.assets.AssetsBundle
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import io.dropwizard.views.ViewBundle

import deploydb.resources.*
import deploydb.health.*

class DeployDBApp extends Application<DeployDBConfiguration> {
    static void main(String[] args) throws Exception {
        new DeployDBApp().run(args);
    }

    @Override
    String getName() {
        return 'deploydb'
    }

    @Override
    public void initialize(Bootstrap<DeployDBConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle())
        bootstrap.addBundle(new ViewBundle())
    }

    @Override
    public void run(DeployDBConfiguration configuration,
                    Environment environment) {

        environment.healthChecks().register('sanity', new SanityHealthCheck())
        environment.jersey().register(new RootResource())
        environment.jersey().register(new ArtifactResource())
    }
}
