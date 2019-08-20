package com.lux.ge.eventserv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lux.ge.eventserv.model.DataFileEvent;
import com.lux.ge.eventserv.repository.EventRepository;

@Service
public class EventServiceImpl implements EventService {
	
	@Autowired
	private EventRepository eventRepository;

	@Override
	public DataFileEvent save(DataFileEvent event) {
		return eventRepository.save(event);
	}

}
