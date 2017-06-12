package de.mjkd.datenbanken.repository;

import de.mjkd.datenbanken.domain.Movie;
import de.mjkd.datenbanken.domain.MovieRole;
import de.mjkd.datenbanken.domain.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by raeste on 11.06.17.
 */
public interface MovieRoleRepository extends MongoRepository<MovieRole, String> {
    List<MovieRole> findByPerson(Person person);
    List<MovieRole> findByMovie(Movie movie);
}
