package com.springboot.kafka.Consumer.models;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.CommitFailedException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class Receiver {
	
	 private static final Logger LOGGER = LoggerFactory
	            .getLogger(Receiver.class);
	            
	            
	            @Autowired
	            KafkaConsumer<String, String> consumer;

	   
	   

		public String kafkaConsumerMessage(String topicName) {
			
		     consumer.subscribe(Arrays.asList(topicName));
			 System.out.println("Subscribed to topic " + consumer.subscription());
		     System.out.println("Subscribed to topic " + topicName);
		
		    	 while (true) {	
				    	
			         ConsumerRecords<String, String> records = consumer.poll(Long.MAX_VALUE);		         
			         System.out.println("Consumer Count : "+records.count());
			        
			        	 consumer.commitSync();			         
				         
				            for (ConsumerRecord<String, String> record : records)
				            {
				            System.out.println("offset = "+record.topic());
				               System.out.printf("offset = %d, key = %s, value = %s\n", 
				               record.offset(), record.key(), record.value());
				            }
				            if(records.count()==0){
					        	 return "No messages where found in kafka";
					         }else{
					        	 //consumer.commitSync();
					        	 return "Message sent successfully";
					         }	 
			         }
		
		 
		}
		
		public String kafkaConsumerMessages(String topicName) {
			
		     consumer.subscribe(Arrays.asList(topicName));
			 System.out.println("Subscribed to topic " + consumer.subscription());
		     System.out.println("Subscribed to topic " + topicName);
		
		
	
		    	 while (true) {	
				    	
			         ConsumerRecords<String, String> records = consumer.poll(Long.MAX_VALUE);		         
			         System.out.println("Consumer Count : "+records.count());
			        
			         //consumer.commitSync();			         
				         
				            for (ConsumerRecord<String, String> record : records)
				            {
				            
				            System.out.println("offset = "+record.topic());
				               System.out.printf("offset = %d, key = %s, value = %s\n", 
				               record.offset(), record.key(), record.value());
				            }
				            if(records.count()==0){
					        	 return "No messages where found in kafka";
					         }else{
					        	 consumer.commitSync();
					        	 return "Message sent successfully";
					         }	 
			         }
		
		 
		}
		
	
}
