package com.lux.ge.fileproc.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lux.ge.fileproc.model.TimeseriesData;
import com.lux.ge.fileproc.model.TimeseriesType;

@RestController
@RequestMapping("kafka")
public class FileProcessorController {
	
	private static final String TOPIC_DATA = "data-topic";
	private static final String TOPIC_NOTIFICATION = "notification-topic";
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

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
	
    @GetMapping("/publish/{guid}/{voltage}/{temperature}")
    public String sendMessage(@PathVariable("guid") final Integer guid, @PathVariable("voltage") final Float voltage, @PathVariable("temperature") final Float temperature) {
    	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    	TimeseriesData timeseriesData = new TimeseriesData(guid, timestamp, TimeseriesType.VOLTAGE.getValue(), voltage);
    	kafkaTemplate.send(TOPIC_DATA, timeseriesData);
    	timeseriesData.setType(TimeseriesType.TEMPERATURE.getValue());
    	timeseriesData.setValue(temperature);
    	kafkaTemplate.send(TOPIC_DATA, timeseriesData);
    	
    	return "Published successfully";
    }

}
