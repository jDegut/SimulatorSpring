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
    private InscriptionService inscriptionService;

    @Autowired
    private IndicatorService indicatorService;

    @Autowired
    private ActionMapper actionMapper;

    @Autowired
    private MissionMapper missionMapper;

    /**
     * Get all actions
     * @return
     */
    @Override
    public List<ActionDto> getAllActions() {
        return actionRepository.findAll().stream()
                .map(actionMapper::toDto)
                .toList();
    }

    @Override
    public List<ActionDto> getAllActionsById(List<Integer> ids) {
        return actionRepository.findAllById(ids).stream()
                .map(actionMapper::toDto)
                .toList();
    }

    /**
     * Get all actions of a mission
     * @param missionDto
     * @return
     */
    @Override
    public List<ActionDto> getAllActionByMission(MissionDto missionDto) {
        Mission mission = missionMapper.toEntity(missionDto);
        List<Action> actions = actionMissionRepository.findAllByFkMission(mission).stream()
                .map(ActionMission::getFkAction).toList();
        return actions.stream().map(actionMapper::toDto).toList();
    }

    /**
     * Get all actions that are not in a mission (add a new action to a mission)
     * @param missionDto
     * @return
     */
    @Override
    public List<ActionDto> getAllOtherActionByMission(MissionDto missionDto) {
        Mission mission = missionMapper.toEntity(missionDto);
        List<Action> actions = actionMissionRepository.findAllByFkMission(mission).stream()
                .map(ActionMission::getFkAction).toList();
        List<Action> allActions = actionRepository.findAll();
        allActions.removeAll(actions);
        return allActions.stream().map(actionMapper::toDto).toList();
    }

    /**
     * Get an action by its id
     * @param id
     * @return
     */
    @Override
    public ActionDto getAction(int id) {
        return actionMapper.toDto(actionRepository.findById(id)
                .orElseThrow(() -> new ActionException("Action not found")));
    }

    /**
     * Add an action to a mission
     * @param mission
     * @param actionDto
     */
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

    /**
     * Create an action
     * @param actionDto
     */
    @Override
    public void createAction(ActionDto actionDto) {
        Action action = actionMapper.toEntity(actionDto);
        if(actionDto.getPreviousActionId() == null)
            action.setFkAction(null);
        actionRepository.save(action);
    }

    /**
     * Remove an action from a mission (idAction)
     * @param mission
     * @param actionId
     */
    @Override
    public void removeAction(Mission mission, int actionId) {
        List<Action> actionMissionsWithHierarchy = findActionMissionWithHierarchy(actionId);
        List<ActionMission> actionMissions = actionMissionRepository.findAllByFkMissionAndFkActionIn(mission, actionMissionsWithHierarchy);
        actionMissionRepository.deleteAllInBatch(actionMissions);
    }

    /**
     * Find all actions with hierarchy (baseAction -> child -> child -> null ?)
     * @param actionId
     * @return
     */
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

    /**
     * Delete all actions of a mission (to delete a mission after)
     * @param mission
     */
    @Override
    public void deleteAllActionMission(Mission mission) {
        List<ActionMission> actionMissions = actionMissionRepository.findAllByFkMission(mission);
        actionMissionRepository.deleteAllInBatch(actionMissions);
    }

    @Override
    public void deleteAction(int id) {
        Action action = actionRepository.findById(id)
                .orElseThrow(() -> new ActionException("Action not found"));
        deleteAllMissionAction(action);
        indicatorService.deleteAllActionIndicator(action);
        inscriptionService.deleteActionInscriptions(action);
        deleteActionParent(action);
        actionRepository.delete(action);
    }

    private void deleteAllMissionAction(Action action) {
        List<ActionMission> actionMissions = actionMissionRepository.findAllByFkAction(action);
        actionMissionRepository.deleteAllInBatch(actionMissions);
    }

    private void deleteActionParent(Action action) {
        Action parent = actionRepository.findByFkAction(action);
        if(parent == null)
            return;
        parent.setFkAction(null);
        actionRepository.save(parent);
    }


}
