package com.springboot.kafka.Consumer;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class Sender {
	
	 private static final Logger LOGGER = LoggerFactory
	            .getLogger(Sender.class);

	    @Autowired
	    private Producer<String,String> producer;
	    
	    
	    public void sendMessage(String topic,String message) throws InterruptedException {
	  
	    	//  for(int i=0  ;i < 2; i++)	    		  
	    		 
	 	         producer.send(new ProducerRecord<String, String>(topic, message));
	 	         System.out.println("Message sent successfully");	 	        
	 	         producer.flush();
	 	               
	      
	    }
	    
	   
	  
	 
}

