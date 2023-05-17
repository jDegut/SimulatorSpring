package fr.polytech.simulatorspring.service;

import fr.polytech.simulatorspring.domain.Action;
import fr.polytech.simulatorspring.domain.ActionMission;
import fr.polytech.simulatorspring.domain.Mission;
import fr.polytech.simulatorspring.dto.ActionDto;
import fr.polytech.simulatorspring.mapper.ActionMapper;
import fr.polytech.simulatorspring.repository.ActionMissionRepository;
import fr.polytech.simulatorspring.repository.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActionService implements IActionService{

	@Autowired
	private ActionRepository actionRepository;

	@Autowired
	private ActionMissionRepository actionMissionRepository;

	@Autowired
	private ActionMapper actionMapper;

	@Override
	public List<ActionDto> getAllActionByMission(Mission mission) {
		List<Action> actions = actionMissionRepository.findAllByFkMission(mission).stream()
				.map(ActionMission::getFkAction).toList();
		return actions.stream().map(actionMapper::toDto).toList();
	}
}
