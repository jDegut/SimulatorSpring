package fr.polytech.simulatorspring.controller;

import fr.polytech.simulatorspring.dto.UserDto;
import fr.polytech.simulatorspring.dto.UserUpdateRequest;
import fr.polytech.simulatorspring.exception.UserException;
import fr.polytech.simulatorspring.security.service.UserDetailsImpl;
import fr.polytech.simulatorspring.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.stream.Collectors;

@Controller
@CrossOrigin
@RequestMapping("/user")
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IUserService userService;

	@GetMapping("/me")
	public ModelAndView getUser() {
		UserDetails user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDto userDto = null;
		try {
			userDto = userService.getUser(user);
		} catch (UserException e) {
			logger.error(e.getMessage());
		}
		return new ModelAndView("user").addObject("user", userDto);
	}

	@PostMapping
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

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Integer id) {
		try {
			userService.deleteUser(id);
		} catch (UserException e) {
			logger.error(e.getMessage());
		}
	}

}
