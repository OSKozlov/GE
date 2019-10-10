package com.lux.ge.stat.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.convert.CassandraConverter;
import org.springframework.data.cassandra.core.convert.CassandraCustomConversions;
import org.springframework.data.cassandra.core.convert.MappingCassandraConverter;

import com.lux.ge.stat.converter.DateToTimestampConverter;

@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {

	public static final String KEYSPACE = "timeseries";
	public static final String CONTACT_POINTS = "cassandra";

	@Override
	public SchemaAction getSchemaAction() {
		return SchemaAction.CREATE_IF_NOT_EXISTS;
	}

	@Override
	protected String getKeyspaceName() {
		return KEYSPACE;
	}
	
	@Override
	protected String getContactPoints() {
		return CONTACT_POINTS;
	}

	@Bean
	CassandraCustomConversions cassandraCustomConversions() {
		List<Converter<?, ?>> converters = new ArrayList<>();
		converters.add(new DateToTimestampConverter());
		return new CassandraCustomConversions(converters);
	}
	
	
	@Override
	public CassandraConverter cassandraConverter() {
		try {
			MappingCassandraConverter mappingCassandraConverter = new MappingCassandraConverter(cassandraMapping());

			mappingCassandraConverter.setCustomConversions(cassandraCustomConversions());

			return mappingCassandraConverter;
		} catch (ClassNotFoundException cause) {
			throw new IllegalStateException(cause);
		}
	}

}
