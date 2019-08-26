package com.lux.ge.stat.repository;

import org.springframework.data.repository.CrudRepository;

import com.lux.ge.stat.model.TimeseriesData;

public interface TSeriesRepository extends CrudRepository<TimeseriesData, String> {

}

