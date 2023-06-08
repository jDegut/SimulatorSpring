package fr.polytech.simulatorspring.service;

import fr.polytech.simulatorspring.domain.Mission;
import fr.polytech.simulatorspring.dto.ActionDto;
import fr.polytech.simulatorspring.dto.MissionDto;
import fr.polytech.simulatorspring.exception.MissionException;
import fr.polytech.simulatorspring.mapper.MissionMapper;
import fr.polytech.simulatorspring.repository.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MissionService implements IMissionService{

	@Autowired
	private MissionRepository missionRepository;

	@Autowired
	private ActionService actionService;

	@Autowired
	private InscriptionService inscriptionService;

	@Autowired
	private MissionMapper missionMapper;

	/**
	 * Get all missions with their actions (ActionDtos in MissionDtos)
	 * @return
	 */
	@Override
	public List<MissionDto> findAllMissions() {
		List<Mission> missions = missionRepository.findAll();
		List<MissionDto> missionDtos = new ArrayList<>();
		for (Mission mission : missions) {
			MissionDto missionDto = missionMapper.toDto(mission);
			List<ActionDto> actionDtos = actionService.getAllActionByMission(missionDto);
			missionDto.setActions(actionDtos);
			missionDtos.add(missionDto);
		}
		return missionDtos;
	}

	/**
	 * Get a mission with its actions (ActionDtos in MissionDto)
	 * @param id
	 * @return
	 */
	@Override
	public MissionDto findMissionById(int id) {
		Mission mission = missionRepository.findById(id)
				.orElseThrow(() -> new MissionException("Mission not found"));
		MissionDto missionDto = missionMapper.toDto(mission);
		List<ActionDto> actionDtos = actionService.getAllActionByMission(missionDto);
		missionDto.setActions(actionDtos);
		return missionDto;
	}

	@Override
	public void createMission(MissionDto missionDto, List<Integer> actionIds) {
		List<ActionDto> actionDtos = actionService.getAllActionsById(actionIds);
		Mission mission = missionMapper.toEntity(missionDto);
		missionRepository.save(mission);
		for (ActionDto actionDto : actionDtos) {
			actionService.addToMission(mission, actionDto);
		}
	}

	/**
	 * Add an action to a mission
	 * @param id
	 * @param actionDto
	 */
	@Override
	public void addToMission(int id, ActionDto actionDto) {
		Mission mission = missionRepository.findById(id)
				.orElseThrow(() -> new MissionException("Mission not found"));
		actionService.addToMission(mission, actionDto);
	}

	/**
	 * Remove an action from a mission
	 * @param missionId
	 * @param actionId
	 */
	@Override
	public void removeAction(int missionId, int actionId) {
		Mission mission = missionRepository.findById(missionId)
				.orElseThrow(() -> new MissionException("Mission not found"));
		actionService.removeAction(mission, actionId);
	}

	/**
	 * Delete a mission
	 * 1 - Delete all actions of the mission
	 * 2 - Delete all inscriptions of the mission
	 * 3 - Delete the mission (not working for an unknown reason)
	 * @param id
	 */
	@Override
	public void deleteMission(int id) {
		Mission mission = missionRepository.findById(id)
						.orElseThrow(() -> new MissionException("Mission not found"));
		actionService.deleteAllActionMission(mission);
		inscriptionService.deleteMissionInscriptions(mission);
		missionRepository.deleteById(id);
	}
}
