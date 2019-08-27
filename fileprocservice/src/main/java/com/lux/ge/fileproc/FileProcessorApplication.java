package com.lux.ge.fileproc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lux.ge.fileproc.services.DirectoryWatchService;

@SpringBootApplication
public class FileProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileProcessorApplication.class, args);
		
		DirectoryWatchService directoryWatchService = ApplicationContextProvider.getContext().getBean(DirectoryWatchService.class);
		if (directoryWatchService != null) {
			directoryWatchService.processEvents();
		}
	}

}