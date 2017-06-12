package de.mjkd.datenbanken.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by raeste on 11.06.17.
 */
@Document(collection = "movieRole")
@TypeAlias("movieRole")
public class MovieRole {

    @Id
    private String id;

    @DBRef
    private Movie movie;

    @DBRef
    private Person person;

    private Role role;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "MovieRole{" +
                "id='" + id + '\'' +
                ", movie=" + movie +
                ", person=" + person +
                ", role=" + role +
                '}';
    }
}
