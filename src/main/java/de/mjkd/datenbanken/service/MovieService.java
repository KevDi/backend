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

    Page<Movie> listAllByPage(Pageable pageable);
    Movie create(Movie movie);
    Movie read(String name);
    Page<Movie> searchByName(String name, Pageable pageRequest);
    List<Person> findActors(Movie movie);
    Page<Movie> findByYear(String year, Pageable pageRequest);
    Page<Movie> findByYearBetween(String yearFrom, String yearTo, Pageable pageRequest);
}
