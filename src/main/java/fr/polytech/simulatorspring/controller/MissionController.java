package fr.polytech.simulatorspring.controller;

import fr.polytech.simulatorspring.dto.ActionDto;
import fr.polytech.simulatorspring.dto.MissionDto;
import fr.polytech.simulatorspring.service.ActionService;
import fr.polytech.simulatorspring.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Arrays;
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
		return new ModelAndView("mission/missions").addObject("missions", missionDtos);
	}

	@GetMapping("/create")
	public ModelAndView createMission() {
		List<ActionDto> actionDtos = actionService.getAllActions();
		return new ModelAndView("mission/createMission").addObject("actions", actionDtos);
	}

	@PostMapping("/create")
	public ModelAndView createMission(@ModelAttribute MissionDto missionDto, @RequestParam("actionList")List<Integer> actionIds) {
		missionService.createMission(missionDto, actionIds);
		return new ModelAndView(new RedirectView("/mission"));
	}

	@GetMapping("/modify/{id}")
	public ModelAndView modifyMission(@PathVariable int id) {
		MissionDto missionDto = missionService.findMissionById(id);
		List<ActionDto> actionDtos = actionService.getAllOtherActionByMission(missionDto);
		return new ModelAndView("mission/modifyMission").addObject("mission", missionDto).addObject("otherActions", actionDtos);
	}

	@PostMapping("/{id}/action/add")
	public ModelAndView addActionToMission(@PathVariable int id, @ModelAttribute ActionDto actionDto) {
		missionService.addToMission(id, actionDto);
		return new ModelAndView(new RedirectView("/mission"));
	}

	@GetMapping("/{id}/remove/{actionId}")
	public ModelAndView removeActionToMission(@PathVariable int id, @PathVariable int actionId, @ModelAttribute ActionDto actionDto) {
		missionService.removeAction(id, actionId);
		return new ModelAndView(new RedirectView("/mission"));
	}

	@GetMapping("/delete/{id}")
	public ModelAndView deleteMission(@PathVariable int id) {
		missionService.deleteMission(id);
		return new ModelAndView(new RedirectView("/mission"));
	}

}
