package fr.polytech.simulatorspring.service;

import fr.polytech.simulatorspring.domain.Mission;

public interface IInscriptionService {

    void deleteUserInscriptions(int idUser);

    void deleteMissionInscriptions(Mission mission);
}
