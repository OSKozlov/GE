package com.lux.ge.eventserv.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.lux.ge.eventserv.model.DataFileEvent;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

	@Bean
	public ConsumerFactory<String, DataFileEvent> tseriesConsumerFactory() {
		Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "ge-ts-data");
		
		JsonDeserializer<DataFileEvent> jsonDeserializer = new JsonDeserializer<>(DataFileEvent.class, false);
		jsonDeserializer.addTrustedPackages("com.lux.ge.fileproc.model");
		
		return new DefaultKafkaConsumerFactory<String, DataFileEvent>(config, new StringDeserializer(), jsonDeserializer);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, DataFileEvent> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, DataFileEvent> factory = new ConcurrentKafkaListenerContainerFactory<String, DataFileEvent>();
		factory.setConsumerFactory(tseriesConsumerFactory());
		return factory;
	}

}
