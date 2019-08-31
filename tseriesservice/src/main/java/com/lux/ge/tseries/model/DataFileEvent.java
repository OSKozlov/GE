package com.lux.ge.tseries.model;

import java.sql.Timestamp;

public class DataFileEvent {
	
	private Timestamp timestamp;

	private String topic;
	
	private String type;
	
	private String fileName;
	
	public DataFileEvent() {
	}
	
	public DataFileEvent(Timestamp timestamp, String topic, String type, String fileName) {
		this.timestamp = timestamp;
		this.topic = topic;
		this.type = type;
		this.fileName = fileName;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
