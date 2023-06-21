package fr.polytech.simulatorspring.controller;

import fr.polytech.simulatorspring.dto.ActionDto;
import fr.polytech.simulatorspring.service.ActionService;
import fr.polytech.simulatorspring.service.IActionService;
import fr.polytech.simulatorspring.service.IIndicatorService;
import fr.polytech.simulatorspring.service.IndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@CrossOrigin
@RequestMapping("/action")
public class ActionController {

	@Autowired
	private IActionService actionService;

	@Autowired
	private IIndicatorService indicatorService;

	@GetMapping
	@PreAuthorize("hasAuthority('admin')")
	public ModelAndView listActions() {
		return new ModelAndView("action/actions")
				.addObject("actions", actionService.getAllActions());
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ModelAndView modifyAction(@PathVariable int id) {
		return new ModelAndView("action/modifyAction")
				.addObject("action", actionService.getAction(id))
				.addObject("indicators", indicatorService.getAllIndicatorsAction(id));
	}

	@GetMapping("/add")
	@PreAuthorize("hasAuthority('admin')")
	public ModelAndView addAction() {
		return new ModelAndView("action/createAction")
				.addObject("actions", actionService.getAllActions());
	}

	@PostMapping("/create")
	@PreAuthorize("hasAuthority('admin')")
	public ModelAndView createAction(@ModelAttribute ActionDto actionDto) {
		actionService.createAction(actionDto);
		return new ModelAndView(new RedirectView("/action"));
	}

	@GetMapping("/delete/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ModelAndView deleteAction(@PathVariable int id) {
		actionService.deleteAction(id);
		return new ModelAndView(new RedirectView("/action"));
	}

	@GetMapping("/{idAction}/remove/{idIndicator}")
	@PreAuthorize("hasAuthority('admin')")
	public ModelAndView deleteIndicator(@PathVariable int idAction, @PathVariable int idIndicator) {
		indicatorService.deleteIndicator(idIndicator);
		return new ModelAndView(new RedirectView("/action/" + idAction));
	}

}
