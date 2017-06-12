package de.mjkd.datenbanken.controller;

import de.mjkd.datenbanken.domain.Movie;
import de.mjkd.datenbanken.domain.Person;
import de.mjkd.datenbanken.repository.MovieRepository;
import de.mjkd.datenbanken.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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
    public Page<Movie> list(Pageable pageable) {
        Page<Movie> movies = movieService.listAllByPage(pageable);
        return movies;
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
    public Page<Movie> searchByName(@RequestParam String name, Pageable pageable) {
        return movieService.searchByName(name, pageable);
    }

    @RequestMapping(value = "/movies/{name}/actors", method = RequestMethod.GET)
    public List<Person> listActors(@PathVariable String name) {
        Movie mov = movieService.read(name);
        return movieService.findActors(mov);
    }

    @RequestMapping(value = "/movies/search/searchByYear", method = RequestMethod.GET)
    public Page<Movie> listMoviesByYear(@RequestParam String year, Pageable pageRequest) {
        return movieService.findByYear(year, pageRequest);
    }

    @RequestMapping(value = "/movies/search/searchByYearBetween", method = RequestMethod.GET)
    public Page<Movie> listMoviesByYear(@RequestParam String yearFrom, @RequestParam String yearTo, Pageable pageRequest) {
        return movieService.findByYearBetween(yearFrom, yearTo, pageRequest);
    }
}
