package com.lux.ge.facade.controller;

import java.io.IOException;
import java.util.List;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lux.ge.facade.model.DataFileEvent;
import com.lux.ge.facade.model.RoleType;
import com.lux.ge.facade.model.StatData;
import com.lux.ge.facade.model.TimeseriesData;
import com.lux.ge.facade.model.User;
import com.lux.ge.facade.services.SecurityService;
import com.lux.ge.facade.services.UserService;
import com.lux.ge.facade.validator.UserValidator;

@RestController
public class AuthenticationController {

    @Autowired 
    private Environment env;
    
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;
    
	private RestTemplate restTemplate = new RestTemplate();
	
	private Logger logger = Logger.getLogger(AuthenticationController.class.getName());

    @GetMapping("/registration")
    public ModelAndView registration(Model model) {
        model.addAttribute("userForm", new User());

        return new ModelAndView("registration");
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public ModelAndView login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return new ModelAndView("login");
    }

    @GetMapping({"/", "/welcome"})
    public ModelAndView welcome(Model model) {
        return new ModelAndView("welcome");
    }
    
	@PostMapping({ "/events" })
	public ModelAndView displayEvents(Model model) {
		
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User user = userService.findByUsername(userName);
		
		if (!RoleType.ADMIN_USER.getRoleName().equals(user.getRole())) {
			return new ModelAndView("welcome");
		}
		
		ResponseEntity<String> response = sendRequest(env.getProperty("eventsrv") + "/getEvents");
        
        ObjectMapper objectMapper = new ObjectMapper();
        List<DataFileEvent> dataFileEvent = null;
        try {
			dataFileEvent = objectMapper.readValue(response.getBody(), new TypeReference<List<DataFileEvent>>() {});
		} catch (IOException e) {
			logger.log(Level.WARN, "Error occured while retrieve data from server. ", e);
		}
        
        ModelAndView modelAndView = new ModelAndView("events");
        modelAndView.addObject("events", dataFileEvent);
        
		return modelAndView;
	}

	@PostMapping({ "/messages" })
	public ModelAndView displayMessages(Model model) {

		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User user = userService.findByUsername(userName);
		
		if (!RoleType.ENGINEER_USER.getRoleName().equals(user.getRole())) {
			return new ModelAndView("welcome");
		}

		ResponseEntity<String> response = sendRequest(env.getProperty("statsrv") + "/getRawData");
        
        ObjectMapper objectMapper = new ObjectMapper();
        List<TimeseriesData> dataFileEvent = null;
        try {
			dataFileEvent = objectMapper.readValue(response.getBody(), new TypeReference<List<TimeseriesData>>() {});
		} catch (IOException e) {
			logger.log(Level.WARN, "Error occured while retrieve data from server. ", e);
		}
        
        ModelAndView modelAndView = new ModelAndView("messages");
        modelAndView.addObject("messages", dataFileEvent);
        
		return modelAndView;
	}

	@PostMapping({ "/statistic" })
	public ModelAndView displayStatistic(Model model) {

		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User user = userService.findByUsername(userName);
		
		if (!RoleType.ENGINEER_USER.getRoleName().equals(user.getRole())) {
			return new ModelAndView("welcome");
		}
        
		ResponseEntity<String> response = sendRequest(env.getProperty("statsrv") + "/getStatistic");
		
        ObjectMapper objectMapper = new ObjectMapper();
        List<StatData> dataFileEvent = null;
        try {
			dataFileEvent = objectMapper.readValue(response.getBody(), new TypeReference<List<StatData>>() {});
		} catch (IOException e) {
			logger.log(Level.WARN, "Error occured while retrieve data from server. ", e);
		}
        
        ModelAndView modelAndView = new ModelAndView("statistic");
        modelAndView.addObject("statistics", dataFileEvent);
        
		return modelAndView;
	}
	
	private ResponseEntity<String> sendRequest(String url) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
		return response;
	}

}
