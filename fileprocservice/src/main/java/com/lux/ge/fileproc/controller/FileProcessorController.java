package com.lux.ge.fileproc.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lux.ge.fileproc.ApplicationContextProvider;
import com.lux.ge.fileproc.services.DirectoryWatchService;

@RestController
public class FileProcessorController {
	
	private static Logger logger = Logger.getLogger(FileProcessorController.class.getName());
	
	@RequestMapping("/test")
    @ResponseBody
    public String test(HttpServletRequest req) {
		
		String fileName = req.getParameter("fileName");
		logger.log(Level.INFO, "Testing file processing " + fileName);
		
		DirectoryWatchService directoryWatchService = ApplicationContextProvider.getContext().getBean(DirectoryWatchService.class);
		if (directoryWatchService != null) {
			try {
				if (fileName != null) {
					// copy file from dir "data" to "/root/file-ge"
					logger.log(Level.INFO, "Copy file from dir 'data' to 'file-ge'");
					Path sourceDir = Paths.get(File.separator + "data" + File.separator + fileName);
					String home = System.getProperty("user.home");
					Path destDir = Paths.get(home + File.separator + "files-ge" + File.separator + fileName);
					Files.copy(sourceDir, destDir, StandardCopyOption.REPLACE_EXISTING);
					
					// process file
					logger.log(Level.INFO, "Start process file");
					directoryWatchService.fileProcessor(fileName);
				}
			} catch (IOException e) {
				logger.log(Level.WARNING, "Error occured while processing file ", e);
			}
		}
		
        return "Test is ok!";
    }

	@RequestMapping("/healthcheck")
    public ModelAndView healthCheck() {
		return new ModelAndView("healthcheck");
    }

}
