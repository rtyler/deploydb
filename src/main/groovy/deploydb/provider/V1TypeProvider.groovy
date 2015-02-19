package deploydb.provider

import groovy.transform.InheritConstructors

import javax.ws.rs.Consumes
import javax.ws.rs.ext.Provider
import io.dropwizard.jersey.jackson.JacksonMessageBodyProvider



/**
 * This provider allows Dropwizard/Jersey to map our custom content-type into
 * proper JSON deserialization for consumption by our APIs
 */
@Provider
@Consumes('application/vnd.deploydb.v1+json')
@InheritConstructors
class V1TypeProvider extends JacksonMessageBodyProvider {
}
