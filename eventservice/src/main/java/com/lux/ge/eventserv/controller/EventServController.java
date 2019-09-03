package com.lux.ge.eventserv.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lux.ge.eventserv.model.DataFileEvent;
import com.lux.ge.eventserv.services.EventService;

@RestController
public class EventServController {
	
	@Autowired 
	private EventService eventService;

	@RequestMapping("/test")
    @ResponseBody
    public String test() {
		
		System.err.println("######## test RUN #######");
		
		DataFileEvent event = new DataFileEvent();
		event.setId(23);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		event.setTimestamp(timestamp);
		event.setTopic("New Data File Processing");
		event.setType("Voltage");
		
		eventService.save(event);
        return "Test is ok!";
    }
	
	@RequestMapping(value = "/getEvents", method = RequestMethod.POST)
    @ResponseBody
    public List<DataFileEvent> getEvents() {
		return eventService.findall();	
	}

}
