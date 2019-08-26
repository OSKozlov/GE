package com.lux.ge.eventserv.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.lux.ge.eventserv.model.DataFileEvent;
import com.lux.ge.eventserv.services.EventService;

@Service
public class KafkaConsumer {
	
	
	@Autowired 
	private EventService timeSeriesService;
	
	@KafkaListener(topics = "notification-topic", groupId = "eventserv-group", containerFactory = "kafkaListenerContainerFactory")
	public void consumeJson(DataFileEvent event) {
		System.out.println("Consumed JSON Message: " + " topic=" + event.getTopic() 
		+ " type=" + event.getType());
		
		timeSeriesService.save(event);
	}

}
