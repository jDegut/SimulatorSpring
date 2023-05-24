package fr.polytech.simulatorspring.controller;

import fr.polytech.simulatorspring.domain.Action;
import fr.polytech.simulatorspring.dto.ActionDto;
import fr.polytech.simulatorspring.dto.MissionDto;
import fr.polytech.simulatorspring.service.ActionService;
import fr.polytech.simulatorspring.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/mission")
public class MissionController {

	@Autowired
	private MissionService missionService;

	@Autowired
	private ActionService actionService;

	@GetMapping
	public ModelAndView listAllMissions() {
		List<MissionDto> missionDtos = missionService.findAllMissions();
		return new ModelAndView("missions").addObject("missions", missionDtos);
	}

	@GetMapping("/modify/{id}")
	public ModelAndView modifyMission(@PathVariable int id) {
		MissionDto missionDto = missionService.findMissionById(id);
		List<ActionDto> actionDtos = actionService.getAllOtherActionByMission(missionDto);
		return new ModelAndView("modifyMission").addObject("mission", missionDto).addObject("otherActions", actionDtos);
	}

	@PostMapping("/{id}/action/add")
	public ModelAndView addActionToMission(@PathVariable int id, @ModelAttribute ActionDto actionDto) {
		System.out.println(actionDto.getId());
		missionService.addToMission(id, actionDto);
		return new ModelAndView(new RedirectView("/mission"));
	}

	@GetMapping("/delete/{id}")
	public ModelAndView deleteMission(@PathVariable int id) {
		missionService.deleteMission(id);
		return new ModelAndView(new RedirectView("/mission"));
	}

}
