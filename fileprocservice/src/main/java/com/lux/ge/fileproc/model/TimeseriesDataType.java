package com.lux.ge.fileproc.model;

public enum TimeseriesDataType {

	VOLTAGE("voltage"),
	TEMPERATURE("temperature");
	
	private String value;
	
	private TimeseriesDataType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}