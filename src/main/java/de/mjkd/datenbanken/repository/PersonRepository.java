package de.mjkd.datenbanken.repository;

import de.mjkd.datenbanken.domain.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by raeste on 11.06.17.
 */
public interface PersonRepository extends MongoRepository<Person, String> {

    Person findByFirstNameAndLastName(String firstName, String lastName);
}
