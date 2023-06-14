package fr.polytech.simulatorspring.controller;

import fr.polytech.simulatorspring.dto.UserDto;
import fr.polytech.simulatorspring.dto.UserUpdateRequest;
import fr.polytech.simulatorspring.exception.UserException;
import fr.polytech.simulatorspring.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IUserService userService;

	@GetMapping("/me")
	@PreAuthorize("hasAnyAuthority('learner', 'admin')")
	public ModelAndView getUser() {
		UserDto userDto = null;
		try {
			userDto = userService.getUser();
		} catch (UserException e) {
			logger.error(e.getMessage());
		}
		return new ModelAndView("user/user").addObject("user", userDto);
	}

	@GetMapping
	@PreAuthorize("hasAuthority('admin')")
	public ModelAndView getUsers() {
		List<UserDto> users = userService.getUsers();
		return new ModelAndView("user/userList").addObject("users", users);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('admin')")
	public ModelAndView updateUser(@ModelAttribute UserUpdateRequest userUpdateRequest, RedirectAttributes redirectAttributes) {
		UserDto newUser;
		String modif = null;
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
		}
		redirectAttributes.addFlashAttribute("modif", modif);
		redirectAttributes.addFlashAttribute("isChanged", isChanged);
		return new ModelAndView(new RedirectView("/user/me"));
	}

	@GetMapping("/modify/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ModelAndView modifyUser(@PathVariable Integer id) {
		UserDto userDto;
		try {
			userDto = userService.getUserById(id);
		} catch (UserException e) {
			throw new RuntimeException(e);
		}
		return new ModelAndView("user/modifyUser").addObject("user", userDto);
	}

	@PostMapping("/role")
	@PreAuthorize("hasAuthority('admin')")
	public ModelAndView updateRole(@ModelAttribute UserUpdateRequest userUpdateRequest) {
		try {
			userService.updateRole(userUpdateRequest);
		} catch (UserException e) {
			throw new RuntimeException(e);
		}
		return new ModelAndView(new RedirectView("/user"));
	}

	@GetMapping("/delete/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ModelAndView deleteUser(@PathVariable Integer id) {
		try {
			userService.deleteUser(id);

		} catch (UserException e) {
			logger.error(e.getMessage());
		}
		return new ModelAndView(new RedirectView("/user"));
	}

}
