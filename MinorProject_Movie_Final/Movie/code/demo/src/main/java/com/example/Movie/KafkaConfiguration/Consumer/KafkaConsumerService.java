package com.example.Movie.KafkaConfiguration.Consumer;

import com.example.Movie.Entity.Rating_Redis;
import com.example.Movie.KafkaConfiguration.Producer.RatingSerializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import java.util.Properties;

public class KafkaConsumerService
{
    @Bean
    Properties kafkaConsumerProperties()
    {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092"); //BOOTSTRAP_SERVERS_CONFIG This is nothing but the Kafka server
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,RatingDeSerializer.class); //When we want to publish the objects to the kafka we need to serialize them to bytes so we need this.

        return properties;
    }

    @Bean
    public ConsumerFactory<String, Rating_Redis> consumerFactory()
    {
        return new DefaultKafkaConsumerFactory(kafkaConsumerProperties());
    }

    /*
         For the Consumer we need to write the listener which will listens to the producer
    */

    public ConcurrentKafkaListenerContainerFactory<String,Rating_Redis> concurrentKafkaListenerContainerFactory()
    {
        ConcurrentKafkaListenerContainerFactory<String,Rating_Redis> concurrentKafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        concurrentKafkaListenerContainerFactory.setConsumerFactory(consumerFactory());
        return concurrentKafkaListenerContainerFactory;
    }


}
