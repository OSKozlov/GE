package com.lux.ge.tseries.services;

import com.lux.ge.tseries.model.TimeseriesData;

public interface TimeSeriesService {
	
	TimeseriesData save(TimeseriesData data);
	
	TimeseriesData findByFileName(String searchTerm);
	
}
