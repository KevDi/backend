package de.mjkd.datenbanken.service;

import de.mjkd.datenbanken.domain.Movie;
import de.mjkd.datenbanken.domain.MovieRole;
import de.mjkd.datenbanken.domain.Person;
import de.mjkd.datenbanken.domain.Role;
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
    public List<Movie> listAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie create(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie read(String id) {
        return movieRepository.findOne(id);
    }

    @Override
    public List<Movie> searchByName(String name ) {
        return movieRepository.findByNameContaining(name);
    }

    @Override
    public List<Person> findActors(Movie movie) {
        List<MovieRole> movieRolesList = movieRoleRepository.findByMovie(movie);
        List<Person> personList = new ArrayList<>();
        for(MovieRole movieRole : movieRolesList) {
            if (movieRole.getRole() == Role.ACTOR){
                personList.add(movieRole.getPerson());
            }
        }
        return personList;
    }

    @Override
    public List<Movie> findByYear(String year ) {
        return movieRepository.findMoviesByYear(year);
    }

    @Override
    public List<Movie> findByYearBetween(String yearFrom, String yearTo  ) {
        return movieRepository.findMoviesByYearBetween(yearFrom, yearTo );
    }

    @Override
    public Person findRegisseur(Movie movie) {
        MovieRole movieRole = movieRoleRepository.findByMovieAndRole(movie, Role.REGISSEUR);
        return movieRole.getPerson();
    }

    @Override
    public void deleteMovie(String id) {
        Movie movie = movieRepository.findOne(id);
        List<MovieRole> roles = movieRoleRepository.findByMovie(movie);
        movieRepository.delete(movie);
        movieRoleRepository.delete(roles);
    }

    @Override
    public Movie update(String id, Movie movie) {
        Movie oldMovie = movieRepository.findOne(id);
        oldMovie.setGenres(movie.getGenres());
        oldMovie.setName(movie.getName());
        oldMovie.setRating(movie.getRating());
        oldMovie.setTags(movie.getTags());
        oldMovie.setYear(movie.getYear());
        return movieRepository.save(oldMovie);
    }
}
