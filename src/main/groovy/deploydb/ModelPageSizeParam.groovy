package deploydb

import io.dropwizard.jersey.params.AbstractParam

import javax.ws.rs.WebApplicationException
import javax.ws.rs.core.Response

public class ModelPageSizeParam extends AbstractParam<Integer> {
    public ModelPageSizeParam(String input) {
        super(input);
    }

    @Override
    protected String errorMessage(String input, Exception e) {
        return '"' + input + "\" is not a valid page size value.";
    }

    @Override
    protected Integer parse(String input) {
        if(input == "0") {
            throw new WebApplicationException(Response.Status.BAD_REQUEST)
        }
        return Integer.valueOf(input);
    }
}
