package com.lux.ge.tseries.listener;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.lux.ge.tseries.model.TimeseriesData;
import com.lux.ge.tseries.model.DataFileEvent;
import com.lux.ge.tseries.services.TimeSeriesService;

@Service
public class KafkaConsumer {
	
	
	@Autowired 
	private TimeSeriesService timeSeriesService;
	
	@Autowired
	private KafkaTemplate<String, DataFileEvent> kafkaTemplate;
	
	@KafkaListener(topics = "data-topic", groupId = "tseries-group", containerFactory = "kafkaListenerContainerFactory")
	public void consumeJson(TimeseriesData data) {
		System.out.println("1 TSERIES SERVICE Consumed JSON Message: " + "guid=" + data.getGuid()  + " timestamp=" + data.getTimestamp() 
		+ " type=" + data.getType()  + " value=" + data.getValue());
		
		timeSeriesService.save(data);
	}

	@KafkaListener(topics = "notification-topic", groupId = "tseries-group", containerFactory = "kafkaEventListenerContainerFactory")
	public void consumeJson(DataFileEvent event) {
		System.out.println("2 TSERIES SERVICE Consumed JSON Message: " + " topic=" + event.getTopic() 
		+ " type=" + event.getType());
		
		if ("Data File Processed".equals(event.getType())) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			DataFileEvent evt = new DataFileEvent(timestamp, "notification-topic", "Timeseries Ready");
			kafkaTemplate.send("notification-topic", evt);
		}
	}
	
}
