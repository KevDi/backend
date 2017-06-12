package de.mjkd.datenbanken.service;

import de.mjkd.datenbanken.domain.Person;
import de.mjkd.datenbanken.repository.MovieRepository;
import de.mjkd.datenbanken.repository.MovieRoleRepository;
import de.mjkd.datenbanken.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by raeste on 12.06.17.
 */
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
        return null;
    }
}
