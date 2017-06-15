package de.mjkd.datenbanken.service;

import de.mjkd.datenbanken.domain.Movie;
import de.mjkd.datenbanken.domain.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


/**
 * Created by raeste on 12.06.17.
 */
public interface PersonService {

    List<Person> listAllByPage();
    Person create(Person person);
    Person read(String firstname, String lastname);
    List<Person> searchByAge(int age);
    List<Person> searchByAgeBetween(int ageFrom, int ageTo);
    List<Person> searchByFirstnameOrLastname(String name);
    List<Movie> findMovies(Person person);
    void deletePerson(String id);
    Person update(String id, Person person);
}
