package de.mjkd.datenbanken.repository;

import de.mjkd.datenbanken.domain.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by raeste on 11.06.17.
 */
public interface MovieRepository extends MongoRepository<Movie, String> {
    Movie findMovieByName(String name);
    Page<Movie> findByNameContaining(String name, Pageable pageRequest);
    Page<Movie> findMoviesByYear(String year, Pageable pageRequest);
    Page<Movie> findMoviesByYearBetween(String year1, String year2, Pageable pageRequest);
}
