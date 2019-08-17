package com.lux.ge.tseries.repository;

import org.springframework.data.repository.CrudRepository;

import com.lux.ge.tseries.model.TimeseriesData;

public interface TSeriesRepository extends CrudRepository<TimeseriesData, String> {

}
