package com.lux.ge.stat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;

@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {

	public static final String KEYSPACE = "timeseries";
	
	@Override
	public SchemaAction getSchemaAction() {
		return SchemaAction.CREATE_IF_NOT_EXISTS;
	}
	
	@Override
	protected String getKeyspaceName() {
		return KEYSPACE;
	}

}
