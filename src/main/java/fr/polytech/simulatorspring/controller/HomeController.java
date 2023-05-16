package fr.polytech.simulatorspring.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@CrossOrigin
@RequestMapping("/")
public class HomeController {

	@GetMapping
	public ModelAndView home() {
		return new ModelAndView("index");
	}

	@GetMapping("/test")
	@PreAuthorize("hasRole('admin')")
	public ModelAndView test() {
		return new ModelAndView("index");
	}

}
