package com.lux.ge.fileproc.controller;

import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lux.ge.fileproc.model.DataFileEvent;
import com.lux.ge.fileproc.model.DataFileEventType;
import com.lux.ge.fileproc.model.TimeseriesData;
import com.lux.ge.fileproc.services.DirectoryWatchService;

@RestController
@RequestMapping("kafka")
public class FileProcessorController {
	
	private static final String TOPIC_DATA = "data-topic";
	private static final String TOPIC_NOTIFICATION = "notification-topic";
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

	@Autowired
	private KafkaTemplate<String, TimeseriesData> kafkaTemplate;
	
	@Autowired
	private KafkaTemplate<String, DataFileEvent> kafkaEventTemplate;
	
	@RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "Test is ok!";
    }

	@RequestMapping("/healthcheck.html")
    public ModelAndView healthCheck() {
		return new ModelAndView("healthcheck");
    }
	
    @GetMapping("/publish")
    public String sendMessage() {
		DirectoryWatchService service = DirectoryWatchService.getInstance(Paths.get("files"));
		List<TimeseriesData> list = service.getTimeseriesData();
		if (list != null && !list.isEmpty()) {
			
			sendNotificationEvent(TOPIC_NOTIFICATION, DataFileEventType.NEW_DATA_FILE_PROCESSING.getValue());
			
			for(TimeseriesData data : list) {
				kafkaTemplate.send(TOPIC_DATA, data);
			}
			
			sendNotificationEvent(TOPIC_NOTIFICATION, DataFileEventType.DATA_FILE_PROCESSED.getValue());
		}
    	return "Published successfully";
    }
    
    private void sendNotificationEvent(String topic, String eventType) {
    	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    	
    	DataFileEvent event = new DataFileEvent(timestamp, topic, eventType);
    	kafkaEventTemplate.send(topic, event);
    }

}
