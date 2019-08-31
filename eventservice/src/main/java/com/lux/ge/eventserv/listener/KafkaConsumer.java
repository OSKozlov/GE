package com.lux.ge.eventserv.listener;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.lux.ge.eventserv.model.DataFileEvent;
import com.lux.ge.eventserv.services.EventService;

@Service
public class KafkaConsumer {
	
	private static Logger logger = Logger.getLogger(KafkaConsumer.class.getName());
	
	@Autowired 
	private EventService eventService;
	
	@KafkaListener(topics = "notification-topic", groupId = "eventserv-group", containerFactory = "kafkaListenerContainerFactory")
	public void consumeJson(DataFileEvent event) {
		logger.log(Level.INFO, "EVENT SERVICE Consumed JSON Message: " + " topic=" + event.getTopic() 
		+ " type=" + event.getType() + " file = " + event.getFileName());
		
		eventService.save(event);
	}

}
