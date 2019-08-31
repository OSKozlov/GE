package com.lux.ge.fileproc.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.lux.ge.fileproc.model.DataFileEvent;
import com.lux.ge.fileproc.model.DataFileEventType;
import com.lux.ge.fileproc.model.TimeseriesData;

/**
 * This class implements watch service that observe folder
 *
 */
@Service
public class DirectoryWatchService {

	private WatchService service;
	private Map<WatchKey, Path> keys;
	private Path dir;
	private Queue<TimeseriesData> timeseriesData;
	
	private static final String TOPIC_DATA = "data-topic";
	private static final String TOPIC_NOTIFICATION = "notification-topic";
	

	@Autowired
	private KafkaTemplate<String, TimeseriesData> kafkaTemplate;
	
	@Autowired
	private KafkaTemplate<String, DataFileEvent> kafkaEventTemplate;
	
	private static Logger logger = Logger.getLogger(DirectoryWatchService.class.getName());

	public DirectoryWatchService() {
		this.dir = Paths.get("files");
		registerDirectory(dir);
	}
	
	public void processEvents() {
		WatchKey watchKey;
		int delay = 5000; // delay for 5 sec.
		int interval = 1000; // iterate every sec.
		Timer timer = new Timer();
		
		try {
			watchKey = service.take();
			do {
				Path eventDir = keys.get(watchKey);
				for (WatchEvent<?> event : watchKey.pollEvents()) {
					WatchEvent.Kind<?> kind = event.kind();
					Path eventPath = (Path) event.context();
					System.out.println(eventDir + ": " + kind + ": " + eventPath.getFileName().toString());
					if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
						sendNotificationEvent(TOPIC_NOTIFICATION, DataFileEventType.NEW_DATA_FILE_PROCESSING.getValue(), eventPath.getFileName().toString());
						
						timeseriesData = readFile(eventPath.getFileName().toString());

						timer.scheduleAtFixedRate(new TimerTask() {
							public void run() {
								TimeseriesData data = timeseriesData.poll();
								if (data != null) {
									kafkaTemplate.send(TOPIC_DATA, data);
								} else {
									sendNotificationEvent(TOPIC_NOTIFICATION, DataFileEventType.DATA_FILE_PROCESSED.getValue(), eventPath.getFileName().toString());
									timer.cancel();
								}
							}
						}, delay, interval);
						
					}
				}
			} while (watchKey.reset());
		} catch (InterruptedException | IOException e) {
			logger.log(Level.WARNING, "Interrupted error occured while watching folder.", e);
		}
	}

	private void registerDirectory(Path path) {
		try {
			this.service = FileSystems.getDefault().newWatchService();
			this.keys = new HashMap<>();
			keys.put(path.register(service, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
					StandardWatchEventKinds.ENTRY_MODIFY), path);
		} catch (IOException e) {
			logger.log(Level.WARNING, "Error occured while creating new watch service.", e);
		}
	}
	
	private Queue<TimeseriesData> readFile(String fileName) throws IOException {
		Queue<TimeseriesData> queue = new LinkedList<>();
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(dir + File.separator + fileName));
			String line = bufferedReader.readLine();
			while (line != null) {
				line = bufferedReader.readLine();
				if (line != null) {
					String[] splitline = line.split(",");
					TimeseriesData timeseriesData = new TimeseriesData();
					timeseriesData.setGuid(Integer.valueOf(splitline[0]));
					timeseriesData.setTimestamp(Timestamp.valueOf(splitline[1]));
					timeseriesData.setType(splitline[2]);
					timeseriesData.setValue(Float.valueOf(splitline[3]));
					timeseriesData.setFileName(fileName);
					queue.add(timeseriesData);
				}
			}
			return queue;
		} catch (IOException e) {
			logger.log(Level.WARNING, "Error occured while reading folder.", e);
		} finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
		}
		return null;
	}
	
    private void sendNotificationEvent(String topic, String eventType, String fileName) {
    	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    	
    	DataFileEvent event = new DataFileEvent(timestamp, topic, eventType, fileName);
    	kafkaEventTemplate.send(topic, event);
    }

    
}
