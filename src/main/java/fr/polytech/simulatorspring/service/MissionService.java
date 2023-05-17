package fr.polytech.simulatorspring.service;

import fr.polytech.simulatorspring.domain.Mission;
import fr.polytech.simulatorspring.dto.ActionDto;
import fr.polytech.simulatorspring.dto.MissionDto;
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
	private MissionMapper missionMapper;

	@Override
	public List<MissionDto> findAllMissions() {
		List<Mission> missions = missionRepository.findAll();
		List<MissionDto> missionDtos = new ArrayList<>();
		for (Mission mission : missions) {
			MissionDto missionDto = missionMapper.toDto(mission);
			List<ActionDto> actionDtos = actionService.getAllActionByMission(mission);
			missionDto.setActions(actionDtos);
			missionDtos.add(missionDto);
		}
		return missionDtos;
	}
}
