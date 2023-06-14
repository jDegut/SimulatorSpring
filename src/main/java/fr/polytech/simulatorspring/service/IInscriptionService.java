package fr.polytech.simulatorspring.service;

import fr.polytech.simulatorspring.domain.*;
import fr.polytech.simulatorspring.dto.InscriptionActionDto;
import fr.polytech.simulatorspring.dto.MissionDto;
import fr.polytech.simulatorspring.dto.UserDto;

import java.util.List;
import java.util.Map;

public interface IInscriptionService {

	List<Inscription> findAllInscriptionsByUser(int idUser);

	Inscription getMissionInscription(int idUser, int idMission);

	Map<MissionDto, List<InscriptionActionDto>> listAllMissionsInscriptionsActionsByUser(UserDto userDto);

	InscriptionActionDto getActionInscription(int idInscription, int idAction);

	void updateScore(int idInscription, int idAction);

	void deleteUserInscriptions(int idUser);

    void deleteMissionInscriptions(Mission mission);

	void deleteActionInscriptions(Action action);
}
