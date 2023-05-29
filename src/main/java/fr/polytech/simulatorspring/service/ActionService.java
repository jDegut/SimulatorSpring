package fr.polytech.simulatorspring.service;

import fr.polytech.simulatorspring.domain.Action;
import fr.polytech.simulatorspring.domain.ActionMission;
import fr.polytech.simulatorspring.domain.ActionMissionId;
import fr.polytech.simulatorspring.domain.Mission;
import fr.polytech.simulatorspring.dto.ActionDto;
import fr.polytech.simulatorspring.dto.MissionDto;
import fr.polytech.simulatorspring.exception.ActionException;
import fr.polytech.simulatorspring.mapper.ActionMapper;
import fr.polytech.simulatorspring.mapper.MissionMapper;
import fr.polytech.simulatorspring.repository.ActionMissionRepository;
import fr.polytech.simulatorspring.repository.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActionService implements IActionService{

	@Autowired
	private ActionRepository actionRepository;

	@Autowired
	private ActionMissionRepository actionMissionRepository;

	@Autowired
	private ActionMapper actionMapper;

	@Autowired
	private MissionMapper missionMapper;

	@Override
	public List<ActionDto> getAllActionByMission(MissionDto missionDto) {
		Mission mission = missionMapper.toEntity(missionDto);
		List<Action> actions = actionMissionRepository.findAllByFkMission(mission).stream()
				.map(ActionMission::getFkAction).toList();
		return actions.stream().map(actionMapper::toDto).toList();
	}

	@Override
	public List<ActionDto> getAllOtherActionByMission(MissionDto missionDto) {
		Mission mission = missionMapper.toEntity(missionDto);
		List<Action> actions = actionMissionRepository.findAllByFkMission(mission).stream()
				.map(ActionMission::getFkAction).toList();
		List<Action> allActions = actionRepository.findAll();
		allActions.removeAll(actions);
		return allActions.stream().map(actionMapper::toDto).toList();
	}

	@Override
	public void addToMission(Mission mission, ActionDto actionDto) {
		Action action = actionRepository.findById(actionDto.getId())
				.orElseThrow(() -> new ActionException("Action not found"));
		List<Action> actions = new ArrayList<>();
		actions.add(action);
		while(action.getFkAction() != null) {
			action = action.getFkAction();
			actions.add(action);
		}
		for(Action a : actions) {
			ActionMission actionMission = new ActionMission();
			ActionMissionId actionMissionId = new ActionMissionId();
			actionMissionId.setFkAction(a.getId());
			actionMissionId.setFkMission(mission.getId());
			actionMission.setId(actionMissionId);
			actionMission.setFkAction(a);
			actionMission.setFkMission(mission);
			actionMissionRepository.save(actionMission);
		}
	}

	@Override
	public void removeAction(Mission mission, int actionId) {
		Action action = actionRepository.findById(actionId)
				.orElseThrow(() -> new ActionException("Action not found"));
		List<Action> actionMissionsWithHierarchy = findActionMissionWithHierarchy(actionId);
		List<ActionMission> actionMissions = actionMissionRepository.findAllByFkMissionAndFkActionIn(mission, actionMissionsWithHierarchy);
		actionMissionRepository.deleteAllInBatch(actionMissions);
	}

	private List<Action> findActionMissionWithHierarchy(int actionId) {
		List<Action> actions = new ArrayList<>();
		Action action = actionRepository.findById(actionId)
				.orElseThrow(() -> new ActionException("Action not found"));
		while(action != null) {
			actions.add(action);
			action = actionRepository.findByFkAction(action);
		}
		return actions;
	}

	@Override
	public void deleteAllActionMission(Mission mission) {
		List<ActionMission> actionMissions = actionMissionRepository.findAllByFkMission(mission);
		actionMissionRepository.deleteAllInBatch(actionMissions);
	}
}
