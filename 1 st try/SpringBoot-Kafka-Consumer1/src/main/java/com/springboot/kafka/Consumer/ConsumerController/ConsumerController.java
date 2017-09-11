package com.springboot.kafka.Consumer.ConsumerController;

import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.kafka.Consumer.models.Receiver;
@RestController
@RequestMapping("/kafka")
public class ConsumerController implements  Serializable {
	@Autowired
	private Receiver receiver;
	
	@RequestMapping("/kafkaConsumer")
	public String kafkaConsumerMessage() {
		String message;
		message=receiver.kafkaConsumerMessage("topicConsumer");		
		return message;
		 
	}
	
	@RequestMapping("/kafkaConsumerProducer")
	public String kafkaConsumerProduce() {
		String message;
		message=receiver.kafkaConsumerMessage("my-replicated-topic");		
		return message;
		 
	}
}
