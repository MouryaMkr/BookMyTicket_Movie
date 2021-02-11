package com.example.Movie.KafkaConfiguration.Consumer;

import com.example.Movie.Entity.Rating_Redis;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

/*
*   The job of Deserializer is to take the byte data and convert to the object
*
*   In This case we are converting the Byte info to the Rating_Redis object.
*
* */



public class RatingDeSerializer implements Deserializer<Rating_Redis>
{

    @Override
    public Rating_Redis deserialize(String s, byte[] bytes)
    {
        Rating_Redis rating_redis = null;
        ObjectMapper objectMapper = new ObjectMapper();
        if(bytes.length == 0)
        {
            return null;
        }
        try {
            rating_redis = objectMapper.readValue(bytes,Rating_Redis.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rating_redis;
    }
}
