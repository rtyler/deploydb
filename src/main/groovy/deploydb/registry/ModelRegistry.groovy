package deploydb.registry

import groovy.transform.TypeChecked
import java.util.concurrent.ConcurrentHashMap

/**
 * Model Registry object
 *
 * It creates the configuration driven model objects. The key for Model 
 * instances is String. Objects are stored in hashMap for later retrieval.
 */
@TypeChecked
class ModelRegistry<T> {
    private ConcurrentHashMap<String, T> modelTable = new ConcurrentHashMap<String, T>()

    /**
     * Insert an extant model in the table
     *
     * @param model          the Model instance
     */
    void put(String name, T model) throws NullPointerException {
        modelTable[name] = model
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
