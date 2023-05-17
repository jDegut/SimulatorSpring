package fr.polytech.simulatorspring.service;

import fr.polytech.simulatorspring.dto.MissionDto;

import java.util.List;

public interface IMissionService {

	List<MissionDto> findAllMissions();

}
