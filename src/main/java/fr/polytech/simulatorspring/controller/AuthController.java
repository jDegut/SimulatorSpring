package fr.polytech.simulatorspring.controller;

import fr.polytech.simulatorspring.dto.JwtResponse;
import fr.polytech.simulatorspring.dto.UserDto;
import fr.polytech.simulatorspring.service.IAuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@CrossOrigin
@RequestMapping
public class AuthController {

	@Autowired
	private IAuthService authService;

	@GetMapping("/auth")
	public ModelAndView auth() {
		return new ModelAndView("auth");
	}

	@RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView login(@ModelAttribute UserDto userDto, HttpServletRequest request) {
		JwtResponse jwt = authService.authenticateUser(userDto);
		HttpSession session = request.getSession(false);
		if(jwt != null && session != null) {
			session.setAttribute("Authorization", "Bearer " + jwt.getToken());
		}
		return new ModelAndView(new RedirectView("/"));
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
