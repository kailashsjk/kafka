import java.util.*;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;

import org.apache.kafka.clients.consumer.OffsetAndMetadata;

import java.util.Collections;

public class ConsumerGroup {
   public static void main(String[] args) throws Exception {
      if(args.length < 2){
         System.out.println("Usage: consumer <topic> <groupname>");
         return;
      }
      
      String topic = args[0].toString();
      String group = args[1].toString();
       
	      Properties props = new Properties();
	      props.put("bootstrap.servers", "localhost:9092");
	      props.put("group.id", group);
	      props.put("enable.auto.commit", "true");
	      props.put("auto.commit.interval.ms", "1000");
	      props.put("session.timeout.ms", "30000");
	      props.put("key.deserializer",          
	         "org.apache.kafka.common.serialization.StringDeserializer");
	      props.put("value.deserializer", 
	         "org.apache.kafka.common.serialization.StringDeserializer");
	      KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
	      consumer.subscribe(Collections.singletonList(topic));
	    
	         
	    //while (true) {
			   
	       /*   ConsumerRecords<String, String> records = consumer.poll(Long.MAX_VALUE);
			System.out.println("count " + records.partitions());
	            for (ConsumerRecord<String, String> record : records){
					 System.out.println("offset = "+record.topic());
	               System.out.printf("offset = %d, key = %s, value = %s\n", 
	               record.offset(), record.key(), record.value());
				   }
				   consumer.commitSync(); */
				   
	     //}     
		 System.out.println("consumers : "+consumer);
		  ConsumerRecords<String, String> records = consumer.poll(1000);
		      System.out.println("iterator : "+records.iterator());
			  System.out.println("partition " + records.partitions());
	         for (TopicPartition partition : records.partitions()) {
                 List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);
                 for (ConsumerRecord<String, String> record : partitionRecords) {
                     System.out.println(record.offset() + ": " + record.value());
                 }
                 long lastOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
                 consumer.commitSync(Collections.singletonMap(partition, new OffsetAndMetadata(lastOffset + 1)));
				 consumer.close();
   }  
}}