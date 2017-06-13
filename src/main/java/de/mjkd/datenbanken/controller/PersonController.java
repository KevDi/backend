package de.mjkd.datenbanken.controller;

import de.mjkd.datenbanken.domain.Movie;
import de.mjkd.datenbanken.domain.Person;
import de.mjkd.datenbanken.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by raeste on 12.06.17.
 */
@RestController
@RequestMapping("/v1")
public class PersonController {

    final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping("/persons")
    public Page<Person> list(Pageable pageable) {
        Page<Person> persons = personService.listAllByPage(pageable);
        return persons;
    }

    @RequestMapping(value = "/persons", method = RequestMethod.POST)
    public Person create(@RequestBody Person person) {
        return personService.create(person);
    }

    @RequestMapping(value = "/persons/{firstname}-{lastname}", method = RequestMethod.GET)
    public Person read(@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname) {
        return personService.read(firstname,lastname);
    }

    @RequestMapping(value = "/persons/search/searchByAge", method = RequestMethod.GET)
    public Page<Person> searchByAge(@RequestParam int age, Pageable pageable) {
        return personService.searchByAge(age, pageable);
    }

    @RequestMapping(value = "/persons/search/searchByAgeBetween", method = RequestMethod.GET)
    public Page<Person> searchByAgeBetween(@RequestParam int ageFrom, @RequestParam int ageTo, Pageable pageable) {
        return personService.searchByAgeBetween(ageFrom, ageTo, pageable);
    }

    @RequestMapping(value = "/persons/search/searchByName", method = RequestMethod.GET)
    public Page<Person> searchByName(@RequestParam String name, Pageable pageable) {
        return personService.searchByFirstnameOrLastname(name, pageable);
    }

    @RequestMapping(value = "/persons/{firstname}-{lastname}/movies", method = RequestMethod.GET)
    public List<Movie> listMovies(@PathVariable String firstname, @PathVariable String lastname) {
        Person person = personService.read(firstname, lastname);
        return personService.findMovies(person);
    }
}
