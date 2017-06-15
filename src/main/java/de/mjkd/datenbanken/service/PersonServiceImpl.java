package de.mjkd.datenbanken.service;

import de.mjkd.datenbanken.domain.Movie;
import de.mjkd.datenbanken.domain.MovieRole;
import de.mjkd.datenbanken.domain.Person;
import de.mjkd.datenbanken.repository.MovieRepository;
import de.mjkd.datenbanken.repository.MovieRoleRepository;
import de.mjkd.datenbanken.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raeste on 12.06.17.
 */
@Service
public class PersonServiceImpl implements PersonService {


    final MovieRepository movieRepository;
    final MovieRoleRepository movieRoleRepository;
    final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(MovieRepository movieRepository, MovieRoleRepository movieRoleRepository, PersonRepository personRepository) {
        this.movieRepository = movieRepository;
        this.movieRoleRepository = movieRoleRepository;
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> listAllByPage() {
        return personRepository.findAll();
    }

    @Override
    public Person create(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person read(String firstname, String lastname) {
        return personRepository.findByFirstNameAndLastName(firstname, lastname);
    }

    @Override
    public List<Person> searchByAgeBetween(int ageFrom, int ageTo ) {
        return personRepository.findByAgeBetween(ageFrom, ageTo);
    }

    @Override
    public List<Person> searchByAge(int age ) {
        return personRepository.findByAge(age);
    }

    @Override
    public List<Movie> findMovies(Person person) {
        List<MovieRole> movieRoleList = movieRoleRepository.findByPerson(person);
        List<Movie> movieList = new ArrayList<>();
        for (MovieRole movieRole : movieRoleList) {
            movieList.add(movieRole.getMovie());
        }
        return movieList;
    }

    @Override
    public List<Person> searchByFirstnameOrLastname(String name) {
        return personRepository.findByFirstNameContainingOrLastNameContaining(name, name);
    }

    @Override
    public void deletePerson(String id) {
        Person person = personRepository.findOne(id);
        List<MovieRole> movieRoles = movieRoleRepository.findByPerson(person);
        personRepository.delete(person);
        movieRoleRepository.delete(movieRoles);
    }

    @Override
    public Person update(String id, Person person) {
        Person oldPerson = personRepository.findOne(id);
        person.setId(oldPerson.getId());
        return personRepository.save(person);
    }
}
