package com.lux.ge.tseries.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.lux.ge.tseries.model.TimeseriesData;

@Service
public class KafkaConsumer {
	
	@KafkaListener(topics = "data-topic", group = "ge-ts-data", containerFactory = "kafkaListenerContainerFactory")
	public void consumeJson(TimeseriesData data) {
		System.out.println("Consumed JSON Message: " + "guid=" + data.getGuid()  + " timestamp=" + data.getTimestamp() 
		+ " type=" + data.getType()  + " value=" + data.getValue());
		
	}

}
