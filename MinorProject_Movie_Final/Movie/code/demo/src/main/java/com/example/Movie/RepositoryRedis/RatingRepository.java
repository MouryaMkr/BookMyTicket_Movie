package com.example.Movie.RepositoryRedis;

import com.example.Movie.Entity.Rating_Redis;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@EnableRedisRepositories
public interface RatingRepository extends CrudRepository<Rating_Redis,Integer>
{

}
