package fr.polytech.simulatorspring.service;

import fr.polytech.simulatorspring.domain.Mission;
import fr.polytech.simulatorspring.dto.ActionDto;

import java.util.List;

public interface IActionService {

	List<ActionDto> getAllActionByMission(Mission mission);

}
