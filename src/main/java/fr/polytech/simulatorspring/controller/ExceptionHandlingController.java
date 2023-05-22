package fr.polytech.simulatorspring.controller;

import fr.polytech.simulatorspring.exception.AuthException;
import fr.polytech.simulatorspring.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExceptionHandlingController {

	@ExceptionHandler(AuthException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ModelAndView handle(AuthException ex) {
		return new ModelAndView("error")
				.addObject("message", ex.getMessage());
	}

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ModelAndView handle(NotFoundException ex) {
		return new ModelAndView("error")
				.addObject("message", ex.getMessage());
	}

}
