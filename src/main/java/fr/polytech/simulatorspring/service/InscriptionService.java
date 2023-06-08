package fr.polytech.simulatorspring.service;

import fr.polytech.simulatorspring.domain.Inscription;
import fr.polytech.simulatorspring.domain.InscriptionAction;
import fr.polytech.simulatorspring.domain.Mission;
import fr.polytech.simulatorspring.repository.InscriptionActionRepository;
import fr.polytech.simulatorspring.repository.InscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscriptionService implements IInscriptionService{

    @Autowired
    private InscriptionRepository inscriptionRepository;

    @Autowired
    private InscriptionActionRepository inscriptionActionRepository;

    /**
     * Delete all inscriptions of a user (to delete a mission)
     * 1 - Delete all inscriptionAction of the user
     * 2 - Delete all inscriptions of the user
     * @param idUser
     */
    @Override
    public void deleteUserInscriptions(int idUser) {
        List<Inscription> inscriptions = inscriptionRepository.findAllByFkUser_Id(idUser);
        List<InscriptionAction> inscriptionActions = inscriptionActionRepository.findAllByFkInscriptionIn(inscriptions);
        inscriptionActionRepository.deleteAllInBatch(inscriptionActions);
        inscriptionRepository.deleteAllInBatch(inscriptions);
    }

    /**
     * Delete all inscriptions of a mission (to delete a mission)
     * 1 - Delete all inscriptionAction of the mission
     * 2 - Delete all inscriptions of the mission
     * @param mission
     */
    @Override
    public void deleteMissionInscriptions(Mission mission) {
        List<Inscription> inscriptions = inscriptionRepository.findAllByFkMission(mission);
        List<InscriptionAction> inscriptionActions = inscriptionActionRepository.findAllByFkInscriptionIn(inscriptions);
        inscriptionActionRepository.deleteAllInBatch(inscriptionActions);
        inscriptionRepository.deleteAllInBatch(inscriptions);
    }
}
