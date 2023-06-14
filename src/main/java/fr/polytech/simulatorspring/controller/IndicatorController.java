package fr.polytech.simulatorspring.controller;

import fr.polytech.simulatorspring.dto.IndicatorDto;
import fr.polytech.simulatorspring.service.IndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/indicator")
@CrossOrigin
public class IndicatorController {

	@Autowired
	private IndicatorService indicatorService;

	@PostMapping("/create")
	public ModelAndView createIndicator(@ModelAttribute IndicatorDto indicatorDto) {
		indicatorService.createIndicator(indicatorDto);
		return new ModelAndView(new RedirectView("/action/" + indicatorDto.getFkActionId()));
	}

}
