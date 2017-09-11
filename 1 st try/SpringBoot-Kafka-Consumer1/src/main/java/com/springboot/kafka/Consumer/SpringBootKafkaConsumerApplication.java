package com.springboot.kafka.Consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan("com.springboot.kafka.*")
public class SpringBootKafkaConsumerApplication {

	public static void main(String[] args){
		SpringApplication.run(SpringBootKafkaConsumerApplication.class, args);
		
	}
}
