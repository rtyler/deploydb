package deploydb.registry

import groovy.transform.TypeChecked
import java.util.concurrent.ConcurrentHashMap

/**
 * Model Registry object
 *
 * It creates the configuration driven model objects. The key for Model 
 * instances is String. Objects are stored in skiplistMap for later retrieval.
 */
@TypeChecked
class ModelRegistry<T> {
    private ConcurrentHashMap<String, T> modelTable = new ConcurrentHashMap<>()

    /**
     * Insert an extant model in the table
     *
     * @param model          the Model instance
     */
    void put(String name, T model) throws NullPointerException {
        modelTable[name] = model
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
        return modelTable.keySet().sort().collect { String key ->
            modelTable[key]
        }
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
