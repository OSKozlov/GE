package com.lux.ge.fileproc.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lux.ge.fileproc.model.TimeseriesData;

/**
 * This class implements watch service that observe folder
 *
 */
public class DirectoryWatchService {

	private WatchService service;
	private Map<WatchKey, Path> keys;
	private Path dir;
	private List<TimeseriesData> timeseriesData;

	private static DirectoryWatchService instance;
	
	private static Logger logger = Logger.getLogger(DirectoryWatchService.class.getName());

	private DirectoryWatchService(Path dir) {
		this.dir = dir;
		registerDirectory(dir);
	}
	
	public static DirectoryWatchService getInstance(Path dir) {
		if (instance == null) {
			instance = new DirectoryWatchService(dir);
		}
		return instance;
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
					System.out.println(eventDir + ": " + kind + ": " + eventPath);
					if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
						timeseriesData = readFile();
					}
				}
			} while (watchKey.reset());
		} catch (InterruptedException e) {
			logger.log(Level.WARNING, "Interrupted error occured while watching folder.", e);
		}
	}

	public List<TimeseriesData> getTimeseriesData() {
		return timeseriesData;
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
	
	private List<TimeseriesData> readFile() {
		List<TimeseriesData> list = new ArrayList<>();
		BufferedReader bufferedReader;
		try {
			bufferedReader = new BufferedReader(new FileReader(dir + File.separator + "ge.txt"));
			String line = bufferedReader.readLine();
			while (line != null) {
				System.out.println(line);
				line = bufferedReader.readLine();
				if (line != null) {
					String[] splitline = line.split(",");
					TimeseriesData timeseriesData = new TimeseriesData();
					timeseriesData.setGuid(Integer.valueOf(splitline[0]));
					timeseriesData.setTimestamp(Timestamp.valueOf(splitline[1]));
					timeseriesData.setType(splitline[2]);
					timeseriesData.setValue(Float.valueOf(splitline[3]));
					list.add(timeseriesData);
				}
			}
			return list;
		} catch (IOException e) {
			logger.log(Level.WARNING, "Error occured while reading folder.", e);
		}
		return null;
	}

}
