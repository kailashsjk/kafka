package com.springboot.kafka.Consumer.models;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;

public class Sender {
	
	    @Autowired
	    private Producer<String,Object> producer;	    
	    
	    public void sendMessage(String topic,Object object) throws InterruptedException {
	    	    		 
	 	         producer.send(new ProducerRecord<String, Object>(topic, object));
	 	         System.out.println("Message sent successfully");	 	        
	 	         producer.flush();	               
	      
	    }	  
	 
}

