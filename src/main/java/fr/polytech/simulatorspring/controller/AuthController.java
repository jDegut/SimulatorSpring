package fr.polytech.simulatorspring.controller;

import fr.polytech.simulatorspring.dto.JwtResponse;
import fr.polytech.simulatorspring.dto.UserDto;
import fr.polytech.simulatorspring.exception.AuthException;
import fr.polytech.simulatorspring.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping
public class AuthController {

	@Autowired
	private IAuthService authService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserDto userDto) {
		JwtResponse response =null;
		try{
			response = authService.authenticateUser(userDto);
		}catch(Exception e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok(response);
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody UserDto userDto) {
		try {
			return ResponseEntity.ok(authService.createUser(userDto));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
