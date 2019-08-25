package com.lux.ge.fileproc;

import java.nio.file.Paths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lux.ge.fileproc.services.DirectoryWatchService;

@SpringBootApplication
public class FileProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileProcessorApplication.class, args);
		
		DirectoryWatchService service = DirectoryWatchService.getInstance(Paths.get("files"));
		service.processEvents();
	}

}