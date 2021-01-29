package com.example.demo.Controller;


import com.example.demo.Entity.Movie;
import com.example.demo.Model.ReviewRequest;
import com.example.demo.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController
{
    @Autowired
    private MovieService movieServiceForUser;

    @GetMapping("/getMovie")
    public Movie getMovie(@RequestParam Integer id)
    {
        return movieServiceForUser.getMovieFromDB(id);
    }

    @GetMapping("/getAllMovies")
    public List<Movie> getAllMovies()
    {
        return movieServiceForUser.getAllMovieFromDB();
    }

    @PatchMapping("/addReview") //This will just adds the reviews to the existing movie so patch is used
    public void addReview(@RequestParam String movieName, @RequestBody ReviewRequest reviewRequest)
    {
        movieServiceForUser.addReview(movieName,reviewRequest);
    }

}