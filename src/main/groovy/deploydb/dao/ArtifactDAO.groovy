package deploydb.dao

import groovy.transform.InheritConstructors
import com.google.common.base.Optional
import io.dropwizard.hibernate.AbstractDAO

import deploydb.models.Artifact


@InheritConstructors
class ArtifactDAO extends AbstractDAO<Artifact> {
    
    //public Optional<Person> findById(Long id) {
    //    return Optional.fromNullable(get(id));
    //}

    //public Person create(Person person) {
    //    return persist(person);
    //}

    //public List<Person> findAll() {
    //    return list(namedQuery("com.example.helloworld.core.Person.findAll"));
    //}
}
