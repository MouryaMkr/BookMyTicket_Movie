package com.example.Movie.Entity;


import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@RedisHash("rating")
public class Rating_Redis
{

    @Id
    Integer id;
    private Double rating;

    public Rating_Redis() {
    }

    public Rating_Redis(Integer movieId, Double rating) {
        this.id = movieId;
        this.rating = rating;
    }

    public Integer getMovieId() {
        return id;
    }

    public void setMovieId(Integer movieId) {
        this.id = movieId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Rating_Redis{" +
                "movieId=" + id +
                ", rating=" + rating +
                '}';
    }
}
