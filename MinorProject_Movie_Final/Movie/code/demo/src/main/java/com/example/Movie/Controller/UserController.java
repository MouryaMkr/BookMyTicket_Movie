package com.example.Movie.Controller;


import com.example.Movie.Entity.Movie;
import com.example.Movie.Model.ReviewRequest;
import com.example.Movie.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController
{
    @Autowired
    private MovieService movieServiceForUser;

    @GetMapping("/user/movie/getMovie")
    public Movie getMovie(@RequestParam Integer id)
    {
        return movieServiceForUser.getMovieFromDB(id);
    }

    @GetMapping("/user/movie/getAllMovies")
    public List<Movie> getAllMovies()
    {
        return movieServiceForUser.getAllMovieFromDB();
    }

    @PatchMapping("/user/movie/addReview") //This will just adds the reviews to the existing movie so patch is used
    public void addReview(@RequestParam String movieName, @RequestBody ReviewRequest reviewRequest)
    {
        movieServiceForUser.addReview(movieName,reviewRequest);
    }

}