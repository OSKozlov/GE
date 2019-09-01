package com.lux.ge.facade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lux.ge.facade.model.User;
//import com.lux.ge.facade.services.UserDetailsService;

@RestController
public class FacadeController {
	
	/*
	 * @Autowired UserDetailsService userDataService;
	 */
	
	@RequestMapping("/test")
    @ResponseBody
    public String test() {
//		User user = userDataService.findByEmail("olek.kozlov@gmail.com");
//		System.err.println("$$$$ user role = " + user.getRole());
        return "Test is ok!";
    }

	@RequestMapping("/healthcheck")
    public ModelAndView healthCheck() {
		return new ModelAndView("healthcheck");
    }

}
