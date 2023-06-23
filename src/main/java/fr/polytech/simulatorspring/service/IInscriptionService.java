package fr.polytech.simulatorspring.service;

import fr.polytech.simulatorspring.domain.Action;
import fr.polytech.simulatorspring.domain.Inscription;
import fr.polytech.simulatorspring.domain.Mission;
import fr.polytech.simulatorspring.dto.InscriptionActionDto;
import fr.polytech.simulatorspring.dto.MissionDto;
import fr.polytech.simulatorspring.dto.UserDto;

import java.util.List;
import java.util.Map;

public interface IInscriptionService {

	List<Inscription> findAllInscriptionsByUser(int idUser);

	List<MissionDto> listAllMissionsInscriptionsActionsByUser(UserDto userDto);

	InscriptionActionDto getActionInscription(int idInscription, int idAction);

	void create(int idMission, UserDto userDto);

	void updateScore(int idInscription, int idAction);

	void deleteUserInscriptions(int idUser);

    void deleteMissionInscriptions(Mission mission);

	void deleteActionInscriptions(Action action);
}
