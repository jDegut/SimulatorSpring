package fr.polytech.simulatorspring.controller;

import fr.polytech.simulatorspring.domain.Inscription;
import fr.polytech.simulatorspring.dto.IndicatorDto;
import fr.polytech.simulatorspring.dto.InscriptionIndicatorDto;
import fr.polytech.simulatorspring.dto.UserDto;
import fr.polytech.simulatorspring.exception.ActionException;
import fr.polytech.simulatorspring.exception.UserException;
import fr.polytech.simulatorspring.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/inscription")
@CrossOrigin
public class InscriptionController {

	private static final Logger logger = LoggerFactory.getLogger(InscriptionController.class);

	@Autowired
	private IInscriptionService inscriptionService;
	@Autowired
	private IMissionService missionService;
	@Autowired
	private IIndicatorService indicatorService;
	@Autowired
	private IUserService userService;

	@GetMapping
	@PreAuthorize("hasAuthority('learner')")
	public ModelAndView listInscriptions() {
		UserDto userDto;
		try {
			userDto = userService.getUser();
			return new ModelAndView("mission/myMissions")
					.addObject("missionsMap", inscriptionService.listAllMissionsInscriptionsActionsByUser(userDto));
		} catch (UserException e) {
			logger.error(e.getMessage());
		}
		return new ModelAndView(new RedirectView("/auth"));
	}

	@GetMapping("/{idInscription}/action/{idAction}")
	@PreAuthorize("hasAuthority('learner')")
	public ModelAndView getUserIndicators(@PathVariable int idInscription, @PathVariable int idAction) {
		return new ModelAndView("action/myAction")
				.addObject("actionInscription", inscriptionService.getActionInscription(idInscription, idAction))
				.addObject("indicatorInscriptions", indicatorService.getAllIndicatorsActionInscription(idInscription, idAction));
	}

	@GetMapping("/{idInscription}/action/{idAction}/indicator/{idIndicator}")
	@PreAuthorize("hasAuthority('learner')")
	public ModelAndView applyIndicatorDone(@PathVariable int idInscription, @PathVariable int idAction, @PathVariable int idIndicator) {
		indicatorService.makeDone(idInscription, idAction, idIndicator);
		inscriptionService.updateScore(idInscription, idAction);
		return new ModelAndView(new RedirectView("/inscription/" + idInscription + "/action/" + idAction));
	}

	@GetMapping("/add")
	@PreAuthorize("hasAuthority('learner')")
	public ModelAndView addInscription() {
		UserDto userDto;
		try {
			userDto = userService.getUser();
			return new ModelAndView("mission/addMission")
					.addObject("missions", missionService.getAllMissionsNotInscribed(userDto));
		} catch (UserException e) {
			logger.error(e.getMessage());
		}
		return new ModelAndView(new RedirectView("/auth"));
	}

	@GetMapping("/add/{idMission}")
	@PreAuthorize("hasAuthority('learner')")
	public ModelAndView sign(@PathVariable int idMission) {
		UserDto userDto;
		try {
			userDto = userService.getUser();
			inscriptionService.create(idMission, userDto);
			return new ModelAndView(new RedirectView("/inscription"));
		} catch (UserException | ActionException e) {
			logger.error(e.getMessage());
		}
		return new ModelAndView(new RedirectView("/auth"));
	}

}
