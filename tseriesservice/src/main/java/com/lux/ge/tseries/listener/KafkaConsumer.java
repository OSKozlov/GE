package com.lux.ge.tseries.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.lux.ge.tseries.model.TimeseriesData;
import com.lux.ge.tseries.services.TimeSeriesService;

@Service
public class KafkaConsumer {
	
	
	@Autowired 
	private TimeSeriesService timeSeriesService;
	
	@KafkaListener(topics = "data-topic", groupId = "ge-ts-data", containerFactory = "kafkaListenerContainerFactory")
	public void consumeJson(TimeseriesData data) {
		System.out.println("Consumed JSON Message: " + "guid=" + data.getGuid()  + " timestamp=" + data.getTimestamp() 
		+ " type=" + data.getType()  + " value=" + data.getValue());
		
		timeSeriesService.save(data);
	}

}
