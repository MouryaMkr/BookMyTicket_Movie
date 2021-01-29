package com.example.demo.Entity;


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

    private String rating;
    private String comment;

    @ManyToOne
    @JsonIgnore
    private Movie movie;

    public Review() {
    }

    public Review(String rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
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
                ", rating='" + rating + '\'' +
                ", comment='" + comment + '\'' +
                ", movie=" + movie +
                '}';
    }
}
