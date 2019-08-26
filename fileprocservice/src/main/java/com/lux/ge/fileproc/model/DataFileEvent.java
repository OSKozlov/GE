package com.lux.ge.fileproc.model;

import java.sql.Timestamp;

public class DataFileEvent {
	
	private Timestamp timestamp;

	private String topic;
	
	private String type;
	
	public DataFileEvent() {
	}
	
	public DataFileEvent(Timestamp timestamp, String topic, String type) {
		this.timestamp = timestamp;
		this.topic = topic;
		this.type = type;
	}
	
	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
