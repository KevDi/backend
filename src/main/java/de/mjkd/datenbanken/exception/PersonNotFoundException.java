package de.mjkd.datenbanken.exception;

/**
 * Created by raeste on 15.06.17.
 */
public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(String message) {
        super(message);
    }
}
