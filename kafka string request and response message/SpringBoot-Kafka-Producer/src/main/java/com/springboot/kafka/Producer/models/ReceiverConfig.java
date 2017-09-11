package com.springboot.kafka.Producer.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@Configuration
@EnableKafka
public class ReceiverConfig {
	

	    @Bean
	    public Properties consumerConfigs() {
	    	 String group = "consumerss";
	    	 Properties props = new Properties();
	    	 	props.put("bootstrap.servers", "localhost:9092");
	 			props.put("group.id", group);
	 			props.put("enable.auto.commit", "true");
	 		 	props.put("auto.commit.interval.ms", "1000");
	 			props.put("session.timeout.ms", "30000");
	 			props.put("key.deserializer",          
	 		         "org.apache.kafka.common.serialization.StringDeserializer");
	 			props.put("value.deserializer", 
	 		         "org.apache.kafka.common.serialization.StringDeserializer");

	        return props;
	    }

	    @Bean
	    public KafkaConsumer<String, String> consumerFactory() {
	        return new KafkaConsumer<String, String>(consumerConfigs());
	    }

	 

	    @Bean
	    public Receiver receiver() {
	        return new Receiver();
	    }
}
