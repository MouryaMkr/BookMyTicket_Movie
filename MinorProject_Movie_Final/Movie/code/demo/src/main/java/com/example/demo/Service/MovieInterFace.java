package com.example.demo.Service;

import com.example.demo.Entity.Movie;
import com.example.demo.Model.MovieRequest;
import com.example.demo.Model.ReviewRequest;

import java.util.List;

public interface MovieInterFace
{
    public void Create(MovieRequest movieRequest);
    public Movie getMovieFromDB(Integer id);
    public void Update(Integer id,MovieRequest movieRequest);
    void deleteMovieFromDB(Integer id);
    public List<Movie> getAllMovieFromDB();
    void addReview(String movieName, ReviewRequest reviewRequest);
}
