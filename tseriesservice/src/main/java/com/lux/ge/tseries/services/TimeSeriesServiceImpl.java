package com.lux.ge.tseries.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lux.ge.tseries.model.TimeseriesData;
import com.lux.ge.tseries.repository.TSeriesRepository;

@Service
public class TimeSeriesServiceImpl implements TimeSeriesService {
	
	@Autowired
	private TSeriesRepository repository; 

	/*
	 * @Override public TimeseriesData getById(String id) { return
	 * repository.findById(id); }
	 */

	@Override
	public TimeseriesData save(TimeseriesData data) {
		return repository.save(data);
	}

}
