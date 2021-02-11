package com.example.Movie.KafkaConfiguration.Producer;

import com.example.Movie.Entity.Rating_Redis;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Objects;

public class RatingSerializer implements Serializer<Rating_Redis>
{

    @Override
    public byte[] serialize(String s, Rating_Redis rating_redis)  /*This function will serialize the object to the byte data (We are producing the data in bytes)*/
    {

        byte[] temp = null;

        ObjectMapper objectMapper = new ObjectMapper();

        if(Objects.isNull(rating_redis))
        {
            return new byte[0];
        }
        try {
            temp =  objectMapper.writeValueAsBytes(rating_redis);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return temp;
    }
}
