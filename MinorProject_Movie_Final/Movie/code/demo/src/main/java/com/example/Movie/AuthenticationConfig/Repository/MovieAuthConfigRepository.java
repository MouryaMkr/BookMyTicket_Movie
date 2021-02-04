package com.example.Movie.AuthenticationConfig.Repository;

import com.example.Movie.AuthenticationConfig.Entity.MovieUserDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieAuthConfigRepository extends CrudRepository<MovieUserDetails,Integer>
{

    @Query(value = "select * from movie_user_details where movie_user_details.user_name = ?1",nativeQuery = true)
    public MovieUserDetails getUser(String name);
}
