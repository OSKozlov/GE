package com.lux.ge.eventserv.services;

import com.lux.ge.eventserv.model.DataFileEvent;

public interface EventService {

	DataFileEvent save(DataFileEvent event);
	
}
