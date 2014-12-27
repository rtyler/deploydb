package deploydb

import com.google.common.collect.ImmutableList

import io.dropwizard.Application
import io.dropwizard.assets.AssetsBundle
import io.dropwizard.db.DataSourceFactory
import io.dropwizard.hibernate.HibernateBundle
import io.dropwizard.hibernate.SessionFactoryFactory
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import io.dropwizard.views.ViewBundle

import deploydb.resources.*
import deploydb.health.*
import deploydb.models.Artifact
import deploydb.dao.ArtifactDAO


class DeployDBApp extends Application<DeployDBConfiguration> {
    static void main(String[] args) throws Exception {
        new DeployDBApp().run(args);
    }

    @Override
    String getName() {
        return 'deploydb'
    }

    private final HibernateBundle<DeployDBConfiguration> hibernate =
            new HibernateBundle<DeployDBConfiguration>(ImmutableList.copyOf([Artifact]), new SessionFactoryFactory()) {
                @Override
                DataSourceFactory getDataSourceFactory(DeployDBConfiguration config) {
                    return config.getDataSourceFactory()
                }
            }


    @Override
    public void initialize(Bootstrap<DeployDBConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle())
        bootstrap.addBundle(new ViewBundle())
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(DeployDBConfiguration configuration,
                    Environment environment) {

        environment.healthChecks().register('sanity', new SanityHealthCheck())
        environment.jersey().register(new RootResource())
        environment.jersey().register(new ArtifactResource())
    }
}
