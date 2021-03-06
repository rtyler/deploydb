package deploydb

import io.dropwizard.Configuration
import io.dropwizard.db.DataSourceFactory
import io.dropwizard.flyway.FlywayFactory

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid
import javax.validation.constraints.NotNull

class DeployDBConfiguration extends Configuration {
    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory()

    @Valid
    private FlywayFactory flyway = new FlywayFactory()

    @Valid
    @NotNull
    private String configDirectory = ""

    @JsonProperty("database")
    DataSourceFactory getDataSourceFactory() {
        return database
    }

    @JsonProperty("database")
    void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
        this.database = dataSourceFactory;
    }

    @JsonProperty("flyway")
    FlywayFactory getFlywayFactory() {
        return flyway
    }

    @JsonProperty("flyway")
    void setFlywayFactory(FlywayFactory flywayFactory) {
        this.flyway = FlywayFactory
    }

    @JsonProperty("configDirectory")
    String getConfigDirectory() {
        return configDirectory
    }

    @JsonProperty("configDirectory")
    void setConfigDirectory(String configDirectory) {
        this.configDirectory = configDirectory
    }
}

