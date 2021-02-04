package com.example.Movie.Controller;

import com.example.Movie.Model.MovieRequest;
import com.example.Movie.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.web.bind.annotation.*;


@RestController
public class AdminController
{
    @Autowired
    private MovieService movieService;


    @PostMapping("/admin/movie/addMovie")
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


    @PutMapping("/admin/movie/updateMovie") //this will updates the entire existing movie son put mapping is used.
    public void updateMovie(@RequestParam Integer id,@RequestBody MovieRequest movie)
    {
        movieService.Update(id,movie);
    }

}
