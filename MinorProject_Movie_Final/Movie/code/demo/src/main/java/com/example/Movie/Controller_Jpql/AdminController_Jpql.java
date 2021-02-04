package com.example.Movie.Controller_Jpql;


import com.example.Movie.Entity.Movie;
import com.example.Movie.RepositoryJpql.MovieRepositoryJpql;
import com.example.Movie.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController_Jpql
{
    @Autowired
    private MovieRepositoryJpql movieRepositoryJpql;

    @Autowired
    private MovieService movieServiceForAdminJpql;


    @PatchMapping("/admin/movie/addPrice") //This will just adds the price to the existing movie so patch is used
    public void addPrice(@RequestParam String movieName,@RequestParam Double price)
    {
        List<Movie> movies = movieRepositoryJpql.getMovieByName(movieName);

        for (Movie movie : movies)
        {
            if(movieName.equals(movie.getName()))
            {
                movieServiceForAdminJpql.addPriceToMovie(movie,price);
            }
        }
    }

    @DeleteMapping("/admin/movie/deleteMovieByName")
    public void deleteMovieByName(@RequestParam String name)
    {
        List<Movie> movies = movieRepositoryJpql.getMovieByName(name);
        movieServiceForAdminJpql.deleteMovieByName(movies);
    }
}
