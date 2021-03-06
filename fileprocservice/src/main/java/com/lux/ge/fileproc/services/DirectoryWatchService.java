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
	private static final int TIMER_DELAY = 5000;
	private static final int TIMER_INTERVAL = 1000;
	

	@Autowired
	private KafkaTemplate<String, TimeseriesData> kafkaTemplate;
	
	@Autowired
	private KafkaTemplate<String, DataFileEvent> kafkaEventTemplate;
	
	private static Logger logger = Logger.getLogger(DirectoryWatchService.class.getName());

	public DirectoryWatchService() {
		String home = System.getProperty("user.home");
		this.dir = Paths.get(home + File.separator + "files-ge");
		File directory = new File(String.valueOf(dir.toString()));
		if (!directory.exists()) {
			directory.mkdir();
		}
		logger.log(Level.INFO, "Look files in the directory: " + dir);
		registerDirectory(dir);
	}
	
	public void processEvents() {
		WatchKey watchKey;
		
		try {
			watchKey = service.take();
			do {
				Path eventDir = keys.get(watchKey);
				for (WatchEvent<?> event : watchKey.pollEvents()) {
					WatchEvent.Kind<?> kind = event.kind();
					Path eventPath = (Path) event.context();
					logger.log(Level.INFO, eventDir + ": " + kind + ": " + eventPath.getFileName().toString());
					if (kind == StandardWatchEventKinds.ENTRY_CREATE) {						
						fileProcessor(eventPath.getFileName().toString());
					}
				}
			} while (watchKey.reset());
		} catch (InterruptedException | IOException e) {
			logger.log(Level.WARNING, "Interrupted error occured while watching folder.", e);
		}
	}

	public void fileProcessor(String fileName) throws IOException {
		Timer timer = new Timer();
		sendNotificationEvent(TOPIC_NOTIFICATION, DataFileEventType.NEW_DATA_FILE_PROCESSING.getValue(), fileName);
		
		timeseriesData = readFile(fileName);

		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				TimeseriesData data = timeseriesData.poll();
				if (data != null) {
					kafkaTemplate.send(TOPIC_DATA, data);
				} else {
					sendNotificationEvent(TOPIC_NOTIFICATION, DataFileEventType.DATA_FILE_PROCESSED.getValue(), fileName);
					timer.cancel();
				}
			}
		}, TIMER_DELAY, TIMER_INTERVAL);
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
