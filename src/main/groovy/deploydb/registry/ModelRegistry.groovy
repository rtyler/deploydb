package deploydb.registry

import com.fasterxml.jackson.annotation.JsonProperty
import com.google.common.io.Resources
import groovy.transform.TypeChecked
import io.dropwizard.configuration.ConfigurationFactory
import io.dropwizard.jackson.Jackson
import io.dropwizard.configuration.ConfigurationException
import io.dropwizard.configuration.ConfigurationParsingException
import io.dropwizard.configuration.ConfigurationValidationException
import java.io.File
import java.util.concurrent.ConcurrentHashMap
import javax.validation.Validation
import javax.validation.Validator

/**
 * Model Registry object
 *
 * It creates the configuration driven model objects. The key for Model 
 * instances is String. Objects are stored in hashMap for later retrieval.
 */
@TypeChecked
class ModelRegistry<T> {
    private ConcurrentHashMap<String, T> modelTable
    private final Validator validator
    private final ConfigurationFactory<T> factory

   /**
    * Creates a new Model Registry for the given class.
    *
    * @param klass          the Model class
    */
    ModelRegistry(Class<T> klass) {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator()
        this.factory = new ConfigurationFactory<>(klass,
                                                  validator,
                                                  Jackson.newObjectMapper(), "")
        this.modelTable = new ConcurrentHashMap<String, T>()
    }

    /**
     * Insert an extant model in the table
     *
     * @param model          the Model instance
     */
    void put(String name, T model) throws NullPointerException {
        modelTable.put(name, model)
    }
    
    /**
     * Instantiate a new Model object by parsing contents of the input file (T) 
     *
     * @param File name, file is present in the resources directory
     */
    T load(String filename)
        throws Exception, ConfigurationParsingException,
               ConfigurationValidationException {
        File inputFile = new File(Resources.getResource(filename).toURI())
        T model = factory.build(inputFile)
        return model
    }

    T loadFromString(String content)
            throws Exception, ConfigurationParsingException,
                    ConfigurationValidationException {
        File.createTempFile('tmp', '.yml').write(content)
        return load('tmp.yml')
    }
    /**
     * Return list of all model objects
     */
    List<T> getAll() {
        return new ArrayList<T>(modelTable.values())
    }

    /**
     * Locate a Model by string name
     *
     * @param name The model instance's name (e.g. "fun-as-a-service")
     */
    T get(String name) {
        return modelTable[name]
    }
}
