package com.lux.ge.eventserv.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

	@Override
	public List<DataFileEvent> findall() {
		Iterable<DataFileEvent> events = eventRepository.findAll();
		List<DataFileEvent> list = StreamSupport.stream(events.spliterator(), false).collect(Collectors.toList());
		System.err.println("#### list = " + list.toString());
		return list;
	}

}
