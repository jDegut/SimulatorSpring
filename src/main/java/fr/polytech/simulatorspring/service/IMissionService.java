package fr.polytech.simulatorspring.service;

import fr.polytech.simulatorspring.dto.ActionDto;
import fr.polytech.simulatorspring.dto.MissionDto;
import fr.polytech.simulatorspring.dto.UserDto;

import java.util.List;

public interface IMissionService {

	List<MissionDto> findAllMissions();

	MissionDto findMissionById(int id);

	List<MissionDto> getAllMissionsNotInscribed(UserDto userDto);

	void createMission(MissionDto missionDto, List<Integer> actionIds);

    void addToMission(int id, ActionDto actionDto);

    void removeAction(int missionId, int actionId);

    void deleteMission(int id);

}
