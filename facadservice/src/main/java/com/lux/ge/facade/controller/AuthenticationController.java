package com.lux.ge.facade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lux.ge.facade.model.RoleType;
import com.lux.ge.facade.model.User;
import com.lux.ge.facade.services.SecurityService;
import com.lux.ge.facade.services.UserService;
import com.lux.ge.facade.validator.UserValidator;

@RestController
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

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
		
		return new ModelAndView("events");
	}

	@PostMapping({ "/messages" })
	public ModelAndView displayMessages(Model model) {

		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User user = userService.findByUsername(userName);
		
		if (!RoleType.ENGINEER_USER.getRoleName().equals(user.getRole())) {
			return new ModelAndView("welcome");
		}

		return new ModelAndView("messages");
	}

	@PostMapping({ "/statistic" })
	public ModelAndView displayStatistic(Model model) {

		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User user = userService.findByUsername(userName);
		
		if (!RoleType.ENGINEER_USER.getRoleName().equals(user.getRole())) {
			return new ModelAndView("welcome");
		}

		return new ModelAndView("statistic");
	}

}
