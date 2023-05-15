package fr.polytech.simulatorspring.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/")
public class HomeController {



	@GetMapping
	public String home() {
		return "Hello World!";
	}

	@GetMapping("/test")
	@PreAuthorize("hasRole('admin')")
	public String test() {
		return "Test okay";
	}

}
