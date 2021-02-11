package com.example.Movie.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "review")
public class Review
{
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer Id;

    private Double rating;
    private String comment;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Movie movie;

    public Review() {
    }

    public Review(Double rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Review{" +
                "Id=" + Id +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", movie=" + movie +
                '}';
    }
}
