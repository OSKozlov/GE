package com.lux.ge.tseries.listener;

import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.lux.ge.tseries.model.TimeseriesData;
import com.lux.ge.tseries.model.DataFileEvent;
import com.lux.ge.tseries.services.TimeSeriesService;

@Service
public class KafkaConsumer {
	
	private static Logger logger = Logger.getLogger(KafkaConsumer.class.getName());
	
	@Autowired 
	private TimeSeriesService timeSeriesService;
	
	@Autowired
	private KafkaTemplate<String, DataFileEvent> kafkaTemplate;
	
	@KafkaListener(topics = "data-topic", groupId = "tseries-group", containerFactory = "kafkaListenerContainerFactory")
	public void consumeJson(TimeseriesData data) {
		logger.log(Level.INFO, "TSERIES SERVICE Consumed JSON Message: " + "guid=" + data.getGuid()  + " timestamp=" + data.getTimestamp() 
		+ " type=" + data.getType()  + " value=" + data.getValue());
		
		timeSeriesService.save(data);
	}

	@KafkaListener(topics = "notification-topic", groupId = "tseries-group", containerFactory = "kafkaEventListenerContainerFactory")
	public void consumeJson(DataFileEvent event) {
		logger.log(Level.INFO, "TSERIES SERVICE Consumed JSON Message: " + " topic=" + event.getTopic() 
		+ " type=" + event.getType() + " file = " + event.getFileName());
		
		if ("Data File Processed".equals(event.getType())) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			DataFileEvent evt = new DataFileEvent(timestamp, "notification-topic", "Timeseries Ready", event.getFileName());
			kafkaTemplate.send("notification-topic", evt);
		}
	}
	
}
