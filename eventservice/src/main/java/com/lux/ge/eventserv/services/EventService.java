package com.lux.ge.eventserv.services;

import java.util.List;

import com.lux.ge.eventserv.model.DataFileEvent;

public interface EventService {

	DataFileEvent save(DataFileEvent event);
	
	List<DataFileEvent> findall();
	
}
