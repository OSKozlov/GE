package com.lux.ge.tseries.services;

import com.lux.ge.tseries.model.TimeseriesData;

public interface TimeSeriesService {

//	TimeseriesData getById(String id);
	
	TimeseriesData save(TimeseriesData data);
	
}
