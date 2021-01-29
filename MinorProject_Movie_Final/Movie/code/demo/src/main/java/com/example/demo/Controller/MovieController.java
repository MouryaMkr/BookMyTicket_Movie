package com.example.demo.Controller;

import com.example.demo.Entity.Movie;
import com.example.demo.Model.MovieRequest;
import com.example.demo.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class MovieController
{
    @Autowired
    private MovieService movieService;


    @PostMapping("/addMovie")
    public void addMovie(@RequestBody MovieRequest movie)
    {
        if(movie != null)
        {
             movieService.Create(movie);
        }
        else
        {
            System.out.println("Can not create an movie since it is null");
        }
    }


    @PutMapping("/updateMovie") //this will updates the entire existing movie son put mapping is used.
    public void updateMovie(@RequestParam Integer id,@RequestBody MovieRequest movie)
    {
        movieService.Update(id,movie);
    }

    @DeleteMapping("/deleteMovie")
    public void deleteMovie(@RequestParam Integer id)
    {
        movieService.deleteMovieFromDB(id);
    }













}
