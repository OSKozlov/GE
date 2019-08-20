package com.lux.ge.fileproc.model;

public enum DataFileEventType {

	NEW_DATA_FILE_PROCESSING("New Data File Processing"), 
	DATA_FILE_PROCESSED("Data File Processed");

	private String value;

	private DataFileEventType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
