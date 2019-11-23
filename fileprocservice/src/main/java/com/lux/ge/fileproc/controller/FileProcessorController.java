package com.lux.ge.fileproc.controller;

import java.io.IOException;
import java.net.http.HttpRequest;
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
