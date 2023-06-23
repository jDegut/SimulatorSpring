package fr.polytech.simulatorspring.controller;

import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<?> home() {
		return ResponseEntity.ok("Hello");
	}

	@GetMapping("/test")
	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<?> test() {
		return ResponseEntity.ok("Admin test good");
	}

}
