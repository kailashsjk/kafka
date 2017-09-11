package com.springboot.kafka.Producer.ProducerController;

import javax.validation.Valid;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.kafka.Producer.models.ConfigProperties;
import com.springboot.kafka.Producer.models.JsonConfiguration;
import com.springboot.kafka.Producer.models.Sender;

@EnableKafka
@Import({ JsonConfiguration.class, ConfigProperties.class })
@RestController
@RequestMapping("/kafka")
public class ProducerController{

	@Autowired
	private Sender sender;
		
	@Autowired
	private ConcurrentKafkaListenerContainerFactory<String, Object> con;	
	
	ConsumerRecord<String,Object> objects = null;
	@RequestMapping("/kafkaProducer")
	public String kafkaProducer(@Valid @RequestBody Object object) throws InterruptedException {		
		sender.sendMessage("fundTransferRequestTopic",object);
		return "Successfully Sent";
	}
	
	
	@KafkaListener(topics = "fundTransferResponseTopic")
	public void listen(ConsumerRecord<String,Object> object){		
		objects=object;
		System.out.println("Received: Value: " + object.value()+" Key : " + object.key()+" Offset : " + object.offset());
	}
	@RequestMapping("/kafkaProducerSuccess")
	public String kafkaSuccess(){	
		
		listen(objects);
		return "Message Received Successfully";
		
		
	}
	
}
