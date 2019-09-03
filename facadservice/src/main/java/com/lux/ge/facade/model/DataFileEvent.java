package com.lux.ge.facade.model;

import java.sql.Timestamp;

public class DataFileEvent {
	
	private int id;
	
	private Timestamp timestamp;

	private String topic;
	
	private String type;
	
	private String filename;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		return filename;
	}

	public void setFileName(String fileName) {
		this.filename = fileName;
	}

}
