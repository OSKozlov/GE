package com.lux.ge.tseries.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.lux.ge.tseries.model.TimeseriesData;
import com.lux.ge.tseries.model.DataFileEvent;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {
	
    @Autowired 
    private Environment env;

	@Bean
	public ConsumerFactory<String, TimeseriesData> tseriesConsumerFactory() {
		Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, env.getProperty("kafkasrv"));
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "tseries-group");
		
		JsonDeserializer<TimeseriesData> jsonDeserializer = new JsonDeserializer<>(TimeseriesData.class, false);
		jsonDeserializer.addTrustedPackages("com.lux.ge.fileproc.model");
		
		return new DefaultKafkaConsumerFactory<String, TimeseriesData>(config, new StringDeserializer(), jsonDeserializer);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, TimeseriesData> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, TimeseriesData> factory = new ConcurrentKafkaListenerContainerFactory<String, TimeseriesData>();
		factory.setConsumerFactory(tseriesConsumerFactory());
		return factory;
	}
	
	@Bean
	public ConsumerFactory<String, DataFileEvent> eventConsumerFactory() {
		Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, env.getProperty("kafkasrv"));
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "tseries-group");
		
		JsonDeserializer<DataFileEvent> jsonDeserializer = new JsonDeserializer<>(DataFileEvent.class, false);
		jsonDeserializer.addTrustedPackages("com.lux.ge.fileproc.model");
		
		return new DefaultKafkaConsumerFactory<String, DataFileEvent>(config, new StringDeserializer(), jsonDeserializer);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, DataFileEvent> kafkaEventListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, DataFileEvent> factory = new ConcurrentKafkaListenerContainerFactory<String, DataFileEvent>();
		factory.setConsumerFactory(eventConsumerFactory());
		return factory;
	}

}
