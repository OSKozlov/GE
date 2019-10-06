package com.lux.ge.stat.config;

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

import com.lux.ge.stat.model.DataFileEvent;


@Configuration
@EnableKafka
public class KafkaConsumerConfig {
	
	@Autowired 
    private Environment env;
	
	@Bean
	public ConsumerFactory<String, DataFileEvent> tseriesConsumerFactory() {
		Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, env.getProperty("kafkasrv"));
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "statserv-group");
		
		JsonDeserializer<DataFileEvent> jsonDeserializer = new JsonDeserializer<>(DataFileEvent.class, false);
		jsonDeserializer.addTrustedPackages("com.lux.ge.fileproc.model");
		jsonDeserializer.addTrustedPackages("com.lux.ge.tseries.model");
		
		return new DefaultKafkaConsumerFactory<String, DataFileEvent>(config, new StringDeserializer(), jsonDeserializer);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, DataFileEvent> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, DataFileEvent> factory = new ConcurrentKafkaListenerContainerFactory<String, DataFileEvent>();
		factory.setConsumerFactory(tseriesConsumerFactory());
		return factory;
	}

}
