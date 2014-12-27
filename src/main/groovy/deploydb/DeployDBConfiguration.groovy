package deploydb

import io.dropwizard.Configuration
import io.dropwizard.db.DataSourceFactory

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.NotEmpty
import javax.validation.Valid
import javax.validation.constraints.NotNull

class DeployDBConfiguration extends Configuration {
    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory()

    @JsonProperty("database")
    DataSourceFactory getDataSourceFactory() {
        return database
    }

    @JsonProperty("database")
    void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
        this.database = dataSourceFactory;
    }
}

