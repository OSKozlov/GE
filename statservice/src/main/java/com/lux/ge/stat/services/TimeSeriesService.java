package com.lux.ge.stat.services;

import com.lux.ge.stat.model.TimeseriesData;

public interface TimeSeriesService {
	
	Iterable<TimeseriesData> findAll();
	
}
