package com.example.Movie.Service.KafkaService;

import com.example.Movie.Entity.Rating_Redis;
import com.example.Movie.RepositoryRedis.RatingRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


/*
*
*   Microservices - also known as the microservice architecture - is an architectural style that structures an application as a collection of services that are.
*                   Highly maintainable and testable. Loosely coupled. Independently deployable.
*                    Organized around business capabilities.
*
*
*   Must Read : If an Big application is having multiple services we will break down to different services and deploy and run them
*               separately because if one of the service breaks another service should not get effect.
*
*               In the real world each micro service will run on the different machine in our pc we will run on the
*               different processes.
*

*   Below Rating service is nothing but the micro service . To give the flavour of micro Service we can create the new project and run it separately
*
* */


@Service
public class RatingService
{

    @Autowired
    RatingRepository ratingRepository;
    ObjectMapper objectMapper = new ObjectMapper();
    /*
    *    Below we are creating the consumer that consumer belongs to the ratingService group and it is listening to the Rating topic.
    *
    * */
    @KafkaListener(topics = {"Rating"},groupId = "ratingService")
    public void insertRatingToRedisCache(String input) throws JsonProcessingException
    {
        Rating_Redis rating_redis = objectMapper.readValue(input,Rating_Redis.class);
        ratingRepository.save(rating_redis); //Saving the rating to the Rating repository.
    }
}
