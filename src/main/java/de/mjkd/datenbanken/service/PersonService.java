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

    Page<Person> listAllByPage(Pageable pageable);
    Person create(Person person);
    Person read(String firstname, String lastname);
    Page<Person> searchByAge(int age, Pageable pageable);
    Page<Person> searchByAgeBetween(int ageFrom, int ageTo, Pageable pageable);
    Page<Person> searchByFirstnameOrLastname(String name, Pageable pageable);
    List<Movie> findMovies(Person person);
}
