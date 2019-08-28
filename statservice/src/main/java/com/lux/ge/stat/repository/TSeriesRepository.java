package com.lux.ge.stat.repository;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lux.ge.stat.model.TimeseriesData;

public interface TSeriesRepository extends CrudRepository<TimeseriesData, String> {

	/*
	 * Finds all messages processed per file
	 * @param searchTerm
	 * @return A list of Timeseries data
	 */
	@Query("SELECT * FROM timeseriesdata WHERE fileName = 'searchTerm'")
    TimeseriesData findByFileName(@Param("searchTerm") String searchTerm);
	
}

