package com.lux.ge.stat.services;

import com.lux.ge.stat.model.StatData;
import com.lux.ge.stat.model.TimeseriesData;

public interface StatService {

	/**
	 * This method calculate how many messages per file processed for whole period
	 * 
	 * @param data
	 * @param fileName
	 * @return message count
	 */
	long calculateMessagesCount(Iterable<TimeseriesData> data, String fileName);
	
	/**
	 * This method calculate max temperature through all messages
	 * 
	 * @param data
	 * @return max temperature
	 */
	int calculateMaxTemperature(Iterable<TimeseriesData> data);
	
	/**
	 * This method calculate average voltage through all messages 
	 * 
	 * @param data
	 * @return average voltage
	 */
	float calculateAverageVoltage(Iterable<TimeseriesData> data);
	
	/**
	 * This method persist statistic data to data base
	 * 
	 * @param data
	 * @return
	 */
	StatData save(StatData data);
	
}
