package com.lux.ge.fileproc.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.lux.ge.fileproc.model.DataFileEvent;
import com.lux.ge.fileproc.model.TimeseriesData;

@Configuration
public class KafkaConfiguration {

	@Bean
	public ProducerFactory<String, TimeseriesData> producerFactory() {
		Map<String, Object> config = new HashMap();
		
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		
		return new DefaultKafkaProducerFactory<String, TimeseriesData>(config);
	}

	@Bean
	public KafkaTemplate<String, TimeseriesData> kafkaTemplate() {
		return new KafkaTemplate<String, TimeseriesData>(producerFactory());
	}
	
	@Bean
	public ProducerFactory<String, DataFileEvent> producerEventFactory() {
		Map<String, Object> config = new HashMap();
		
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		
		return new DefaultKafkaProducerFactory<String, DataFileEvent>(config);
	}

	@Bean
	public KafkaTemplate<String, DataFileEvent> kafkaEventTemplate() {
		return new KafkaTemplate<String, DataFileEvent>(producerEventFactory());
	}

}
