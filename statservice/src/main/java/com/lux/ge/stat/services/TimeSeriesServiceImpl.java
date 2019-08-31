package com.lux.ge.stat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lux.ge.stat.model.TimeseriesData;
import com.lux.ge.stat.repository.TSeriesRepository;

@Service
public class TimeSeriesServiceImpl implements TimeSeriesService {
	
	@Autowired
	private TSeriesRepository tSeriesRepository;
	
	@Override
	public Iterable<TimeseriesData> findAll() {
		return tSeriesRepository.findAll();
	}

	@Override
	public TimeseriesData findByFileName(String searchTerm) {
		return tSeriesRepository.findByFileName(searchTerm);
	}

}
