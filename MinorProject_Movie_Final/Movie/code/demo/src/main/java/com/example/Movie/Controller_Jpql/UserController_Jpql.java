package com.example.Movie.Controller_Jpql;


import com.example.Movie.Entity.Movie;
import com.example.Movie.RepositoryJpql.MovieRepositoryJpql;
import com.example.Movie.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController_Jpql
{

    @Autowired
    private MovieRepositoryJpql movieRepositoryJpql;

    @Autowired
    private MovieService movieServiceForUserJpql;

    @GetMapping("/user/movie/getMovieByName")
    public List<Movie> getMovieByName(@RequestParam String name)
    {
        return movieRepositoryJpql.getMovieByName(name);
    }

    @GetMapping("/user/movie/getMovieByNameAndLang")
    public Movie getMovieByNameAndLang(@RequestParam String name,@RequestParam String lang)
    {
        return movieRepositoryJpql.getMovieByNameAndLang(name,lang);
    }

    @GetMapping("/user/movie/getMoviesByPriceInAscendingOrder")
    public List<Movie> getMoviesByPriceInAscendingOrder()
    {
        return movieRepositoryJpql.getMoviesByPriceInAscendingOrder();
    }

    @GetMapping("/user/movie/getMoviesByPriceInDescendingOrder")
    public List<Movie> getMoviesByPriceInDescendingOrder()
    {
        return movieRepositoryJpql.getMoviesByPriceInDescendingOrder();
    }



}
