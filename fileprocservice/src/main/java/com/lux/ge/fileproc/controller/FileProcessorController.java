package com.lux.ge.fileproc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lux.ge.fileproc.model.TimeseriesData;

@RestController
@RequestMapping("kafka")
public class FileProcessorController {
	
	private static final String TOPIC_DATA = "data-topic";
	private static final String TOPIC_NOTIFICATION = "notification-topic";

	@Autowired
	private KafkaTemplate<String, TimeseriesData> kafkaTemplate;
	
	@RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "Test is ok!";
    }

	@RequestMapping("/healthcheck.html")
    public ModelAndView healthCheck() {
		return new ModelAndView("healthcheck");
    }
	
    @GetMapping("/publish/{message}")
    public String sendMessage(@PathVariable("message") final String message) {
    	kafkaTemplate.send(TOPIC_DATA, new TimeseriesData(123, "Test", "Test", 46436546));
    	return "Published successfully";
    }

}
