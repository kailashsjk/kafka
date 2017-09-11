package com.springboot.kafka.Consumer;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.client.RestTemplate;
import com.springboot.kafka.Consumer.models.ConfigProperties;
import com.springboot.kafka.Consumer.models.JsonConfiguration;
import com.springboot.kafka.Consumer.models.Sender;

@SpringBootApplication
@EnableKafka
@Import({ JsonConfiguration.class, ConfigProperties.class })
public class SpringBootKafkaConsumerApplication {
	
	public static void main(String[] args) throws BeansException, InterruptedException {
		
		SpringApplication.run(SpringBootKafkaConsumerApplication.class, args);
		
	}
	
	@Bean
	public Listener listener() {
		return new Listener();
	}
	
	public static class Listener {		
		
		@Autowired
		private Sender sender;
		
		@Autowired
		private RestTemplate restTemplate;
		
		@KafkaListener(topics = "fundTransferRequestTopic")
		public void listen(ConsumerRecord<String, Object> object) {
			System.out.println("Received: Value: " + object.value()+" Offset: "+object.offset());
			try {
				Map<String, Object> jsonValues = new HashMap<String, Object>();
			    jsonValues.put("ReferenceId", "123");
			    jsonValues.put("ErrorCode", "1245");
			    jsonValues.put("Error_Desc" , "Successfully Sent");
			    Object objectreturn=jsonValues;
				sender.sendMessage("fundTransferResponseTopic",objectreturn);
				String msg=	restTemplate.getForObject("http://localhost:8077/kafka/kafkaProducerSuccess",String.class);
				System.out.println("In Consumer second msg : "+msg);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
	
	
}
