package de.mjkd.datenbanken.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by raeste on 11.06.17.
 */
@Document(collection = "movies")
@TypeAlias("movie")
public class Movie {

    @Id
    private String id;

    @Indexed
    private String name;

    private List<String> genres;

    private List<String> tags;

    @Indexed
    private double rating;

    private String year;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", genres=" + genres +
                ", tags=" + tags +
                ", rating=" + rating +
                ", year='" + year + '\'' +
                '}';
    }
}
