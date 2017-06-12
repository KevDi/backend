package de.mjkd.datenbanken.service;

import de.mjkd.datenbanken.domain.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * Created by raeste on 12.06.17.
 */
public interface PersonService {

    Page<Person> listAllByPage(Pageable pageable);
    Person create(Person person);
    Person read(String firstname, String lastname);
}
