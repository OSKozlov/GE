package com.lux.ge.fileproc.model;

public enum TimeseriesType {

	VOLTAGE("voltage"),
	TEMPERATURE("temperature");
	
	private String value;
	
	private TimeseriesType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}