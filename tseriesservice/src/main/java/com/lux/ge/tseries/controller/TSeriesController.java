package com.lux.ge.tseries.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("kafka")
public class TSeriesController {
	
	@RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "Test is ok!";
    }

	@RequestMapping("/healthcheck.html")
    public ModelAndView healthCheck() {
		return new ModelAndView("healthcheck");
    }

}
