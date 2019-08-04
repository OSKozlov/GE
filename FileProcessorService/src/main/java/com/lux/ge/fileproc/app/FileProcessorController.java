package com.lux.ge.fileproc.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lux.ge.fileproc.model.TimeseriesData;

@Controller
public class FileProcessorController {
	
	private static final String TOPIC_DATA = "Data Topic";
	private static final String TOPIC_NOTIFICATION = "Notification Topic";

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
    @RequestMapping("/healthCheck")
    @ResponseBody
    public String healthCheck() {
        return "I'm healthy!";
    }

    @GetMapping("/publish/{message}")
    public String sendMessage(@PathVariable("message") final String message) {
//    	new TimeseriesData(1, "", "", 46436546)
    	kafkaTemplate.send(TOPIC_DATA, message);
    	return "Published successfully";
    }

}