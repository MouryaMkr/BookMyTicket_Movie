package com.example.Movie.KafkaConfiguration.Producer;

import com.example.Movie.Entity.Rating_Redis;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Properties;


public class KafkaProducerService
{

    /*
        First we need the kafka properties.
        In the producer part we will write the SERIALIZER
        In the Consumer part we will write the DESERIALIZER
     */

    @Bean
    Properties kafkaProperties()
    {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092"); //BOOTSTRAP_SERVERS_CONFIG This is nothing but the Kafka server
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, RatingSerializer.class); //When we want to publish the objects to the kafka we need to serialize them to bytes so we need this.

        return properties;
    }

    /*Next we need the producer factory and the kafka template.*/

    /*producer factory(This will need the kafka  properties)*/
    @Bean
    public ProducerFactory<String, Rating_Redis> producerFactory()
    {
        return new DefaultKafkaProducerFactory(kafkaProperties());
    }

    /*kafka template(This will need the kafka factory*/
    @Bean
    public KafkaTemplate<String,Rating_Redis> kafkaTemplate()
    {
        return new KafkaTemplate(producerFactory());
    }

    /* Redis will also follows the same configuration pattern */
}
