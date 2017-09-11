package com.springboot.kafka.Consumer.models;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SenderConfig {

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
	      props.put("value.serializer", JsonSerializer.class);

        return props;
    }
    
    @Bean
	public RestTemplate restTemplate() {
	RestTemplate restTemplate = new RestTemplate();
	return restTemplate;
	}
    
    @Bean
    public Producer<String,Object> producerFactory() {
        return new KafkaProducer<String, Object>(producerConfigs());
    }

 

    @Bean
    public Sender sender() {
        return new Sender();
    }

}
