package com.lux.ge.stat.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.lux.ge.stat.model.DataFileEvent;
import com.lux.ge.stat.model.TimeseriesData;
import com.lux.ge.stat.services.TimeSeriesService;

@Service
public class KafkaConsumer {
	
	@Autowired 
	private TimeSeriesService timeSeriesService;

	@KafkaListener(topics = "notification-topic", groupId = "statserv-group", containerFactory = "kafkaListenerContainerFactory")
	public void consumeJson(DataFileEvent event) {
		System.out.println("STAT SERVICE Consumed JSON Message: " + " topic=" + event.getTopic() 
		+ " type=" + event.getType());
		
		if ("Timeseries Ready".equals(event.getType())) {
			Iterable<TimeseriesData> data = timeSeriesService.findAll();
		}

	}
}
