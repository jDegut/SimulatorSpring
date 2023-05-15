package fr.polytech.simulatorspring.controller;

import fr.polytech.simulatorspring.dto.UserDto;
import fr.polytech.simulatorspring.exception.UserException;
import fr.polytech.simulatorspring.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IUserService userService;

	@PutMapping
	public UserDto updateUser(UserDto userDto) {
		try {
			return userService.updateUser(userDto);
		} catch (UserException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('admin')")
	public void deleteUser(@PathVariable Integer id) {
		try {
			userService.deleteUser(id);
		} catch (UserException e) {
			logger.error(e.getMessage());
		}
	}

}
