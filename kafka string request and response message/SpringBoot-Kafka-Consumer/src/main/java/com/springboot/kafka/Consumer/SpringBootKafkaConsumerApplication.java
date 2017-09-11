package com.springboot.kafka.Consumer;

import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.listener.config.ContainerProperties;
import org.springframework.web.client.RestTemplate;

import com.springboot.kafka.Consumer.models.CommonConfiguration;
import com.springboot.kafka.Consumer.models.ConfigProperties;

@SpringBootApplication
@EnableKafka
@Import({ CommonConfiguration.class, ConfigProperties.class })
public class SpringBootKafkaConsumerApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootKafkaConsumerApplication.class, args);
	}

	@Bean
	public Listener listener() {
		return new Listener();
	}
	
	@Bean
	public RestTemplate restTemplate() {
	RestTemplate restTemplate = new RestTemplate();
	return restTemplate;
	}
	@Bean
	public KafkaMessageListenerContainer<String, String> container(
			ConsumerFactory<String, String> consumerFactory,
			ConfigProperties config) {
		ContainerProperties containerProperties = new ContainerProperties(
				config.getTopic());
		containerProperties.setMessageListener(listener());
		return new KafkaMessageListenerContainer<>(consumerFactory,
				containerProperties);
	}

	public static class Listener implements MessageListener<String, String> {

		private final CountDownLatch latch = new CountDownLatch(1);
		
		@Autowired
		private Sender sender;
		
		@Autowired
		private RestTemplate restTemplate;
		
		@Override
		public void onMessage(ConsumerRecord<String, String> record) {
			System.out.println("value: " + record.value() + " Offset: "
					+ record.offset() + " Partition: " + record.partition());
			
			try {
				
				sender.sendMessage("topicProducer",record.value());
				
				String msg=restTemplate.getForObject("http://localhost:8077/kafka/kafkaProducerSuccess",String.class);
				
				System.out.println("Inside on Message: "+msg);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.latch.countDown();
			
			
			
		}
		
		

	}
}
