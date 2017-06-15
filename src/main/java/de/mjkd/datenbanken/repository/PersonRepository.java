package de.mjkd.datenbanken.repository;

import de.mjkd.datenbanken.domain.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by raeste on 11.06.17.
 */
public interface PersonRepository extends MongoRepository<Person, String> {

    Person findByFirstNameAndLastName(String firstName, String lastName);
    List<Person> findByAge(int age);
    List<Person> findByAgeBetween(int ageFrom, int ageTo);
    List<Person> findByFirstNameContainingOrLastNameContaining(String name,String lname);

}
