package de.mjkd.datenbanken.controller;

import com.sun.corba.se.spi.protocol.RequestDispatcherDefault;
import de.mjkd.datenbanken.domain.Movie;
import de.mjkd.datenbanken.domain.Person;
import de.mjkd.datenbanken.exception.PersonNotFoundException;
import de.mjkd.datenbanken.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    public List<Person> list(Pageable pageable) {
        List<Person> persons = personService.listAllByPage();
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
    public List<Person> searchByAge(@RequestParam int age ) {
        return personService.searchByAge(age);
    }

    @RequestMapping(value = "/persons/search/searchByAgeBetween", method = RequestMethod.GET)
    public List<Person> searchByAgeBetween(@RequestParam int ageFrom, @RequestParam int ageTo) {
        return personService.searchByAgeBetween(ageFrom, ageTo );
    }

    @RequestMapping(value = "/persons/search/searchByName", method = RequestMethod.GET)
    public List<Person> searchByName(@RequestParam String name ) {
        return personService.searchByFirstnameOrLastname(name );
    }

    @RequestMapping(value = "/persons/{firstname}-{lastname}/movies", method = RequestMethod.GET)
    public List<Movie> listMovies(@PathVariable String firstname, @PathVariable String lastname) {
        Person person = personService.read(firstname, lastname);
        if (person == null) {
            throw new PersonNotFoundException("Person with name " + firstname + " " + lastname + " not found");
        }
        return personService.findMovies(person);
    }

    @RequestMapping(value = "/persons/{id}", method = RequestMethod.DELETE)
    public void deletePerson(@PathVariable String id) {
        personService.deletePerson(id);
    }

    @RequestMapping(value = "/persons/{id}", method = RequestMethod.PUT)
    public Person updatePerson(@PathVariable String id, @RequestBody Person person) {
        return personService.update(id, person);
    }

    @ExceptionHandler(PersonNotFoundException.class)
    public void handlePersonNotFound(PersonNotFoundException exception, HttpServletResponse respone) throws IOException {
        respone.sendError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }
}
