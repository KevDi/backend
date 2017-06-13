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
 * Created by raeste on 11.06.17.
 */
@Service
public class MovieServiceImpl implements MovieService {

    final MovieRepository movieRepository;
    final MovieRoleRepository movieRoleRepository;
    final PersonRepository personRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, MovieRoleRepository movieRoleRepository, PersonRepository personRepository) {
        this.movieRepository = movieRepository;
        this.movieRoleRepository = movieRoleRepository;
        this.personRepository = personRepository;
    }

    @Override
    public Page<Movie> listAllByPage(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    @Override
    public Movie create(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie read(String name) {
        return movieRepository.findMovieByName(name);
    }

    @Override
    public Page<Movie> searchByName(String name, Pageable pageRequest) {
        return movieRepository.findByNameContaining(name, pageRequest);
    }

    @Override
    public List<Person> findActors(Movie movie) {
        List<MovieRole> movieRolesList = movieRoleRepository.findByMovie(movie);
        List<Person> personList = new ArrayList<>();
        for(MovieRole movieRole : movieRolesList) {
            personList.add(movieRole.getPerson());
        }
        return personList;
    }

    @Override
    public Page<Movie> findByYear(String year, Pageable pageRequest) {
        return movieRepository.findMoviesByYear(year,pageRequest);
    }

    @Override
    public Page<Movie> findByYearBetween(String yearFrom, String yearTo, Pageable pageRequest) {
        return movieRepository.findMoviesByYearBetween(yearFrom, yearTo, pageRequest);
    }
}
