package deploydb.resources

import java.io.File
import java.util.*
import java.io.IOException
import javax.validation.Validation
import javax.validation.Validator
import io.dropwizard.jackson.Jackson
import com.google.common.io.Resources;
import io.dropwizard.configuration.ConfigurationFactory
import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.configuration.ConfigurationException
import io.dropwizard.configuration.ConfigurationParsingException
import io.dropwizard.configuration.ConfigurationValidationException
import java.util.concurrent.ConcurrentHashMap
import java.lang.IllegalArgumentException

/**
 * Model Registry object
 *
 * It creates the configuration driven model objects. The key for Model 
 * instances is String. Objects are stored in hashMap for later retrieval.
 */
class ModelRegistry<T> {
    private final ConcurrentHashMap<String, T> modelTable
    private final Validator validator
    private final ConfigurationFactory<T> factory

   /**
    * Creates a new Model Registry for the given class.
    *
    * @param klass          the Model class
    */
    public ModelRegistry(Class<T> klass) {
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
    void insert(T model) throws IllegalArgumentException {
        //if (model.name == null) throw IllegalArgumentException
        modelTable.put(model.name, model)
    }
    
    /**
     * Instantiate a new Model object by parsing contents of the input file (T) 
     *
     * @param File name, file is present in the resources directory
     */
    T createFromFile(String filename)
        throws Exception, ConfigurationParsingException,
               ConfigurationValidationException {
        File inputFile = new File(Resources.getResource(filename).toURI())
        T model = factory.build(inputFile)
        this.insert(model)
        return model
    }

    /**
     * Return list of all model objects
     */
    List<T> getAllModels() {
        return new ArrayList<T>(modelTable.values())
    }

    /**
     * Locate a Model by string name
     *
     * @param name The model instance's name (e.g. "fun-as-a-service")
     */
    T getModelByName(String name) {
        return modelTable[name]
    }
}
