package fr.polytech.simulatorspring.controller;

import fr.polytech.simulatorspring.dto.UserDto;
import fr.polytech.simulatorspring.dto.UserUpdateRequest;
import fr.polytech.simulatorspring.exception.UserException;
import fr.polytech.simulatorspring.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IUserService userService;

	@GetMapping("/me")
	@PreAuthorize("hasAnyAuthority('learner', 'admin')")
	public ResponseEntity<?> getUser() {
		UserDto userDto;
		try {
			userDto = userService.getUser();
		} catch (UserException e) {
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok(userDto);
	}

	@GetMapping
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<?> getUsers() {
		List<UserDto> users;
		try {
			users = userService.getUsers();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok(users);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<?> updateUser(@RequestBody UserUpdateRequest userUpdateRequest) {
		UserDto newUser;
		String modif;
		boolean isChanged = true;
		try {
			newUser = userService.updateUser(userUpdateRequest);
			modif = "Mot de passe changé avec succès !";
			if(newUser == null) {
				modif = "Mauvais ancien mot de passe";
				isChanged = false;
			}
		} catch (UserException e) {
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		if(isChanged) {
			return ResponseEntity.ok(modif);
		} else {
			return ResponseEntity.badRequest().body(modif);
		}
	}

	@GetMapping("/modify/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<?> modifyUser(@PathVariable Integer id) {
		UserDto userDto;
		try {
			userDto = userService.getUserById(id);
		} catch (UserException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok(userDto);
	}

	@PostMapping("/role")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<?> updateRole(@ModelAttribute UserUpdateRequest userUpdateRequest) {
		try {
			userService.updateRole(userUpdateRequest);
		} catch (UserException e) {
			ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok("Rôle changé avec succès !");
	}

	@GetMapping("/delete/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
		try {
			userService.deleteUser(id);
		} catch (UserException e) {
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok("Utilisateur supprimé avec succès !");
	}

}
