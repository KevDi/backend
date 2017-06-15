package de.mjkd.datenbanken.controller;

import de.mjkd.datenbanken.domain.Movie;
import de.mjkd.datenbanken.domain.Person;
import de.mjkd.datenbanken.exception.MovieNotFoundException;
import de.mjkd.datenbanken.exception.PersonNotFoundException;
import de.mjkd.datenbanken.repository.MovieRepository;
import de.mjkd.datenbanken.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by raeste on 11.06.17.
 */
@RestController
@RequestMapping("/v1")
public class MovieController {

    final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @RequestMapping("/movies")
    public List<Movie> listAll() {
        return movieService.listAll();
    }

    @RequestMapping(value = "/movies", method = RequestMethod.POST)
    public Movie create(@RequestBody Movie movie) {
        return movieService.create(movie);
    }

    @RequestMapping(value = "/movies/{name}", method = RequestMethod.GET)
    public Movie read(@PathVariable String name) {
        return movieService.read(name);
    }

    @RequestMapping(value = "/movies/search/searchByName", method = RequestMethod.GET)
    public List<Movie> searchByName(@RequestParam String name) {
        return movieService.searchByName(name);
    }

    @RequestMapping(value = "/movies/{name}/actors", method = RequestMethod.GET)
    public List<Person> listActors(@PathVariable String name) {
        Movie mov = movieService.read(name);
        if (mov == null) {
            throw new MovieNotFoundException("Movie with name " + name + " not found");
        }
        return movieService.findActors(mov);
    }

    @RequestMapping(value = "/movies/{name}/regisseur", method = RequestMethod.GET)
    public Person readRegisseur(@PathVariable String name) {
        Movie mov = movieService.read(name);
        if (mov == null) {
            throw new MovieNotFoundException("Movie with name " + name + " not found");
        }
        return movieService.findRegisseur(mov);
    }

    @RequestMapping(value = "/movies/search/searchByYear", method = RequestMethod.GET)
    public List<Movie> listMoviesByYear(@RequestParam String year ) {
        return movieService.findByYear(year);
    }

    @RequestMapping(value = "/movies/search/searchByYearBetween", method = RequestMethod.GET)
    public List<Movie> listMoviesByYear(@RequestParam String yearFrom, @RequestParam String yearTo ) {
        return movieService.findByYearBetween(yearFrom, yearTo);
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.DELETE)
    public void deleteMovie(@PathVariable String id) {
        movieService.deleteMovie(id);
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.PUT)
    public Movie updateMovie(@PathVariable String id, @RequestBody Movie movie) {
        return movieService.update(id, movie);
    }

    @ExceptionHandler(MovieNotFoundException.class)
    public void handleMovieNotFound(MovieNotFoundException exception, HttpServletResponse respone) throws IOException {
        respone.sendError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }
}
