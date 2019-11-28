package com.lux.ge.stat.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lux.ge.stat.model.StatData;
import com.lux.ge.stat.model.TimeseriesData;
import com.lux.ge.stat.services.StatService;
import com.lux.ge.stat.services.TimeSeriesService;

@RestController
public class StatController {

	@Autowired 
	private StatService statService;
	
	@Autowired 
	private TimeSeriesService timeSeriesService;
	
	@RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "Test is ok!";
    }

	@RequestMapping(value = "/getStatistic", method = RequestMethod.GET)
    @ResponseBody
    public List<StatData> getStatistic() {
		return statService.findAll();	
	}
	
	@RequestMapping(value = "/getRawData", method = RequestMethod.GET)
    @ResponseBody
    public List<TimeseriesData> getRawData() {
		Iterable<TimeseriesData> timeSeriesData = timeSeriesService.findAll();
		List<TimeseriesData> list = StreamSupport.stream(timeSeriesData.spliterator(), false).collect(Collectors.toList());
		return list;
	}
	
}
