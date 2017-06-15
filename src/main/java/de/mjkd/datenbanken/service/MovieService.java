package de.mjkd.datenbanken.service;

import de.mjkd.datenbanken.domain.Movie;
import de.mjkd.datenbanken.domain.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by raeste on 11.06.17.
 */
public interface MovieService {

    List<Movie> listAll();
    Movie create(Movie movie);
    Movie read(String name);
    List<Movie> searchByName(String name );
    List<Person> findActors(Movie movie);
    Person findRegisseur(Movie movie);
    List<Movie> findByYear(String year );
    List<Movie> findByYearBetween(String yearFrom, String yearTo);
    void deleteMovie(String id);
    Movie update(String id, Movie movie);
}
