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

    @Override
    public void deleteUserInscriptions(int idUser) {
        List<Inscription> inscriptions = inscriptionRepository.findAllByFkUser_Id(idUser);
        List<InscriptionAction> inscriptionActions = inscriptionActionRepository.findAllByFkInscriptionIn(inscriptions);
        inscriptionActionRepository.deleteAllInBatch(inscriptionActions);
        inscriptionRepository.deleteAllInBatch(inscriptions);
    }

    @Override
    public void deleteMissionInscriptions(Mission mission) {
        List<Inscription> inscriptions = inscriptionRepository.findAllByFkMission(mission);
        List<InscriptionAction> inscriptionActions = inscriptionActionRepository.findAllByFkInscriptionIn(inscriptions);
        inscriptionActionRepository.deleteAllInBatch(inscriptionActions);
        inscriptionRepository.deleteAllInBatch(inscriptions);
    }
}
