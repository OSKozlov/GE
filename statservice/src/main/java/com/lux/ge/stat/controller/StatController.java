package com.lux.ge.stat.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lux.ge.stat.ApplicationContextProvider;
import com.lux.ge.stat.model.TimeseriesData;
import com.lux.ge.stat.services.TimeSeriesService;

@RestController
public class StatController {

	@RequestMapping("/test")
    @ResponseBody
    public String test() {
		
		TimeSeriesService timeSeriesService = ApplicationContextProvider.getContext().getBean(TimeSeriesService.class);
		Iterable<TimeseriesData> data = timeSeriesService.findAll();
		System.out.println("$$$$$ data = " + data);
        return "Test is ok!";
    }
	
}
