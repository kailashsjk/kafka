package com.springboot.kafka.Producer.ProducerController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.kafka.Producer.models.Sender;

@RestController
@RequestMapping("/kafka")
public class ProducerController {

	@Autowired
	private Sender sender;
	

	@RequestMapping("/kafkaProducer")
	public String kafkaProducer() throws InterruptedException {		
			sender.sendMessage("topicConsumer");
	         return "Successfully Sent";
	}
	
	@RequestMapping("/kafkaProducerConsume")
	public String kafkaProducerConsume() throws InterruptedException {		
			sender.sendMessage("my-replicated-topic");
	         return "Successfully Sent";
	}
	

}
