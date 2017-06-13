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
    public Page<Person> listAllByPage(Pageable pageable) {
        return personRepository.findAll(pageable);
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
    public Page<Person> searchByAgeBetween(int ageFrom, int ageTo, Pageable pageable) {
        return personRepository.findByAgeBetween(ageFrom, ageTo, pageable);
    }

    @Override
    public Page<Person> searchByAge(int age, Pageable pageable) {
        return personRepository.findByAge(age, pageable);
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
    public Page<Person> searchByFirstnameOrLastname(String name, Pageable pageable) {
        return personRepository.findByFirstNameContainingOrLastNameContaining(name, name, pageable);
    }
}
