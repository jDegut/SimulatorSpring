package fr.polytech.simulatorspring.controller;

import fr.polytech.simulatorspring.dto.ActionDto;
import fr.polytech.simulatorspring.service.ActionService;
import fr.polytech.simulatorspring.service.IndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@CrossOrigin
@RequestMapping("/action")
public class ActionController {

	@Autowired
	private ActionService actionService;

	@Autowired
	private IndicatorService indicatorService;

	@GetMapping
	public ModelAndView listActions() {
		return new ModelAndView("action/actions")
				.addObject("actions", actionService.getAllActions())
				.addObject("indicators", indicatorService.getAllIndicators());
	}

	@GetMapping("/{id}")
	public ModelAndView modifyAction(@PathVariable int id) {
		return new ModelAndView("action/modifyAction")
				.addObject("action", actionService.getAction(id))
				.addObject("indicators", indicatorService.getAllIndicatorsAction(id));
	}

	@GetMapping("/add")
	public ModelAndView addAction() {
		return new ModelAndView("action/createAction")
				.addObject("actions", actionService.getAllActions());
	}

	@PostMapping("/create")
	public ModelAndView createAction(@ModelAttribute ActionDto actionDto) {
		actionService.createAction(actionDto);
		return new ModelAndView(new RedirectView("/action"));
	}

	@GetMapping("/delete/{id}")
	public ModelAndView deleteAction(@PathVariable int id) {
		actionService.deleteAction(id);
		return new ModelAndView(new RedirectView("/action"));
	}

	@GetMapping("/{idAction}/remove/{idIndicator}")
	public ModelAndView deleteIndicator(@PathVariable int idAction, @PathVariable int idIndicator) {
		indicatorService.deleteIndicator(idIndicator);
		return new ModelAndView(new RedirectView("/action/" + idAction));
	}

}
