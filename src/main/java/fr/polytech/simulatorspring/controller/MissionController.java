package fr.polytech.simulatorspring.controller;

import fr.polytech.simulatorspring.dto.MissionDto;
import fr.polytech.simulatorspring.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/mission")
public class MissionController {

	@Autowired
	private MissionService missionService;

	@GetMapping
	public ModelAndView listAllMissions() {
		List<MissionDto> missionDtos = missionService.findAllMissions();
		return new ModelAndView("missions").addObject("missions", missionDtos);
	}

}
