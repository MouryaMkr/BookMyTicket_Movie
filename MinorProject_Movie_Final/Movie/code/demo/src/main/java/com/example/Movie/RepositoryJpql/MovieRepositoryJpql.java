package com.example.Movie.RepositoryJpql;


import com.example.Movie.Entity.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface MovieRepositoryJpql extends CrudRepository<Movie,Integer>
{

    @Query("SELECT m FROM Movie m WHERE m.name = ?1 ")
    public List<Movie> getMovieByName(String name);

    @Query("SELECT m FROM Movie m WHERE m.name = ?1 and m.language =?2")
    public Movie getMovieByNameAndLang(String name,String lang);

    @Query(value = "SELECT * FROM Movie ORDER BY price",nativeQuery = true)
    public List<Movie>   getMoviesByPriceInAscendingOrder();

    @Query(value = "SELECT * FROM Movie ORDER BY price DESC",nativeQuery = true)
    public List<Movie>   getMoviesByPriceInDescendingOrder();

}
