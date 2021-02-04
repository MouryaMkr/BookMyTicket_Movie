package com.example.Movie.Repository;

import com.example.Movie.Entity.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface MovieRepository extends CrudRepository<Movie, Integer>
{

}


