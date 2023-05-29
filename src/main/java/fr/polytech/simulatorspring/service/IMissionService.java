package fr.polytech.simulatorspring.service;

import fr.polytech.simulatorspring.dto.ActionDto;
import fr.polytech.simulatorspring.dto.MissionDto;

import java.util.List;

public interface IMissionService {

	List<MissionDto> findAllMissions();

	MissionDto findMissionById(int id);

	void addToMission(int id, ActionDto actionDto);

    void removeAction(int missionId, int actionId);

    void deleteMission(int id);

}
