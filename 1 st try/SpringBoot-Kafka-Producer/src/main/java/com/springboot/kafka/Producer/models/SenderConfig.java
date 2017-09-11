package com.springboot.kafka.Producer.models;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SenderConfig {
	
	@Value("localhost:9092")
    private String bootstrapServers;

    @Bean
    public Properties producerConfigs() {
       
        Properties props = new Properties();
	      props.put("bootstrap.servers", "localhost:9092");
	      props.put("acks", "all");
	      props.put("retries", 0);
	      props.put("batch.size", 16384);
	      props.put("linger.ms",0);
	      props.put("buffer.memory", 33554432);
	  
	      props.put("key.serializer", 
	         "org.apache.kafka.common.serialization.StringSerializer");
	      props.put("value.serializer", 
	         "org.apache.kafka.common.serialization.StringSerializer");

        return props;
    }

    @Bean
    public Producer<String,String> producerFactory() {
        return new KafkaProducer<String, String>(producerConfigs());
    }

 

    @Bean
    public Sender sender() {
        return new Sender();
    }

}
