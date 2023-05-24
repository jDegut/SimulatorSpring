package fr.polytech.simulatorspring.service;

import fr.polytech.simulatorspring.domain.Action;
import fr.polytech.simulatorspring.domain.Mission;
import fr.polytech.simulatorspring.dto.ActionDto;
import fr.polytech.simulatorspring.dto.MissionDto;

import java.util.List;

public interface IActionService {

	List<ActionDto> getAllActionByMission(MissionDto missionDto);

	List<ActionDto> getAllOtherActionByMission(MissionDto missionDto);

	void addToMission(Mission mission, ActionDto actionDto);

}
