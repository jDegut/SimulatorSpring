package fr.polytech.simulatorspring.controller;

import fr.polytech.simulatorspring.dto.IndicatorDto;
import fr.polytech.simulatorspring.service.IIndicatorService;
import fr.polytech.simulatorspring.service.IndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/indicator")
@CrossOrigin
public class IndicatorController {

	@Autowired
	private IIndicatorService indicatorService;

	@PostMapping("/create")
	@PreAuthorize("hasAuthority('admin')")
	public ModelAndView createIndicator(@ModelAttribute IndicatorDto indicatorDto) {
		indicatorService.createIndicator(indicatorDto);
		return new ModelAndView(new RedirectView("/action/" + indicatorDto.getFkActionId()));
	}

}
