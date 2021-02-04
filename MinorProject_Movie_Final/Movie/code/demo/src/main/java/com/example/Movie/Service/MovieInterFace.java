package com.example.Movie.Service;

import com.example.Movie.Entity.Movie;
import com.example.Movie.Model.MovieRequest;
import com.example.Movie.Model.ReviewRequest;

import java.util.List;

public interface MovieInterFace
{
    public void Create(MovieRequest movieRequest);
    public Movie getMovieFromDB(Integer id);
    public void Update(Integer id,MovieRequest movieRequest);
    void deleteMovieFromDB(Integer id);
    public List<Movie> getAllMovieFromDB();
    void addReview(String movieName, ReviewRequest reviewRequest);
    public void addPriceToMovie(Movie movie, Double price);
    public void deleteMovieByName(List<Movie> movies);
}
