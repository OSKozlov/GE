package com.lux.ge.facade.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class FacadeController {

	@RequestMapping("/test")
	@ResponseBody
	public String test() {
		return "Test is ok!";
	}

	@RequestMapping("/healthcheck")
	public ModelAndView healthCheck() {
		return new ModelAndView("healthcheck");
	}

}
