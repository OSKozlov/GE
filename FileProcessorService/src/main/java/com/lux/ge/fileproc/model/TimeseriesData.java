package com.lux.ge.fileproc.model;

public class TimeseriesData {

	private int guid;
	private String timestamp;
	private String type;
	private Number value;
	
	public TimeseriesData(int guid, String timestamp, String type, Number value) {
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
	
	public String getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public Number getValue() {
		return value;
	}
	
	public void setValue(Number value) {
		this.value = value;
	}
}
