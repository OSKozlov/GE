package com.lux.ge.tseries.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.lux.ge.tseries.model.DataFileEvent;


@Configuration
public class KafkaProducerConfig {
	
    @Autowired 
    private Environment env;

	@Bean
	public ProducerFactory<String, DataFileEvent> producerEventFactory() {
		Map<String, Object> config = new HashMap();
		
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, env.getProperty("kafkasrv"));
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		
		return new DefaultKafkaProducerFactory<String, DataFileEvent>(config);
	}

	@Bean
	public KafkaTemplate<String, DataFileEvent> kafkaEventTemplate() {
		return new KafkaTemplate<String, DataFileEvent>(producerEventFactory());
	}

}