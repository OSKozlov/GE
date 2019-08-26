package com.lux.ge.stat.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.lux.ge.stat.model.DataFileEvent;

@Service
public class KafkaConsumer {

	@KafkaListener(topics = "notification-topic", groupId = "ge-ts-data", containerFactory = "kafkaListenerContainerFactory")
	public void consumeJson(DataFileEvent event) {
		System.out.println("STAT SERVICE Consumed JSON Message: " + " topic=" + event.getTopic() 
		+ " type=" + event.getType());
		

	}
}
