package de.mjkd.datenbanken.exception;

/**
 * Created by raeste on 15.06.17.
 */
public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(String s) { super(s);}
}
