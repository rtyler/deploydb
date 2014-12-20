package deploydb

import io.dropwizard.Configuration
import io.dropwizard.db.DataSourceFactory

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.NotEmpty
import javax.validation.Valid
import javax.validation.constraints.NotNull

class DeployDBConfiguration extends Configuration {
    //@Valid
    //@NotNull
    //@JsonProperty("database")
    //private DataSourceFactory database = new DataSourceFactory()
}

