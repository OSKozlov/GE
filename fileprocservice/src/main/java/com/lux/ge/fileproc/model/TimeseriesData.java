package com.lux.ge.fileproc.model;

import java.sql.Timestamp;

public class TimeseriesData {

	private int guid;
	private Timestamp timestamp;
	private String type;
	private Float value;
	
	public TimeseriesData() {
	}

	public TimeseriesData(int guid, Timestamp timestamp, String type, Float value) {
		super();
		this.guid = guid;
		this.timestamp = timestamp;
		this.type = type;
		this.value = value;
	}

	public int getGuid() {
		return guid;
	}

	public void setGuid(int guid) {
		this.guid = guid;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

}