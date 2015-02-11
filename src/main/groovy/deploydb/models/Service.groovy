package deploydb.models

import com.google.common.collect.Lists
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotNull
import org.hibernate.validator.constraints.NotEmpty

class Service {

    @NotNull
    @JsonProperty
    String name

    @NotEmpty
    @JsonProperty
    List<String> artifacts

    @NotEmpty
    @JsonProperty
    List<String> pipelines

    @JsonProperty
    List<String> promotions
}
