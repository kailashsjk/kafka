package com.springboot.kafka.Producer.models;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "kafka")
public class ConfigProperties {

	private String brokerAddress;

	private String topic;
	
	public String getBrokerAddress() {
		return this.brokerAddress;
	}

	public void setBrokerAddress(String brokerAddress) {
		this.brokerAddress = brokerAddress;
	}

	public String getTopic() {
		return this.topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}


}
