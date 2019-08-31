package com.lux.ge.stat.model;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("StatData")
public class StatData {

	@PrimaryKey
	private String fileName;
	private long messageCount;
	private int maxTemperature;
	private float averageVoltage;
	
	public StatData() {
	}

	public StatData(String fileName, long messageCount, int maxTemperature, float averageVoltage) {
		super();
		this.fileName = fileName;
		this.messageCount = messageCount;
		this.maxTemperature = maxTemperature;
		this.averageVoltage = averageVoltage;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getMessageCount() {
		return messageCount;
	}

	public void setMessageCount(long messageCount) {
		this.messageCount = messageCount;
	}

	public int getMaxTemperature() {
		return maxTemperature;
	}

	public void setMaxTemperature(int maxTemperature) {
		this.maxTemperature = maxTemperature;
	}

	public float getAverageVoltage() {
		return averageVoltage;
	}

	public void setAverageVoltage(float averageVoltage) {
		this.averageVoltage = averageVoltage;
	}

}
