package com.lux.ge.tseries.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lux.ge.tseries.model.TimeseriesData;
import com.lux.ge.tseries.repository.TSeriesRepository;

@Service
public class TimeSeriesServiceImpl implements TimeSeriesService {
	
	@Autowired
	private TSeriesRepository repository; 

	@Override
	public TimeseriesData save(TimeseriesData data) {
		return repository.save(data);
	}

	@Override
	public TimeseriesData findByFileName(String fileName) {
		return repository.findByFileName(fileName);
	}

}
