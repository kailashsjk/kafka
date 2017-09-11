package com.springboot.kafka.Producer.ProducerController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.kafka.Producer.models.Receiver;
import com.springboot.kafka.Producer.models.Sender;

@RestController
@RequestMapping("/kafka")
public class ProducerController{

	@Autowired
	private Sender sender;
	
	@Autowired
	private Receiver receiver;

	@RequestMapping("/kafkaProducer")
	public String kafkaProducer() throws InterruptedException {		
			sender.sendMessage("topicConsumer");
	         return "Successfully Sent";
	}
	
	@RequestMapping("/kafkaProducerSuccess")
	public String kafkaConsumerMessage() {
		String message;
		
	
		message=receiver.kafkaConsumerMessage("topicProducer");	
		
		return message;
		 
	}
	
	
	

}
