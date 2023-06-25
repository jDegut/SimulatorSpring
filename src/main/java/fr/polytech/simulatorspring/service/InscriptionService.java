package fr.polytech.simulatorspring.service;

import fr.polytech.simulatorspring.domain.*;
import fr.polytech.simulatorspring.dto.InscriptionActionDto;
import fr.polytech.simulatorspring.dto.MissionDto;
import fr.polytech.simulatorspring.dto.UserDto;
import fr.polytech.simulatorspring.exception.MissionException;
import fr.polytech.simulatorspring.mapper.InscriptionActionMapper;
import fr.polytech.simulatorspring.mapper.MissionMapper;
import fr.polytech.simulatorspring.mapper.UserMapper;
import fr.polytech.simulatorspring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

@Service
public class InscriptionService implements IInscriptionService{

    @Autowired
    private InscriptionRepository inscriptionRepository;

    @Autowired
    private InscriptionActionRepository inscriptionActionRepository;

    @Autowired
    private InscriptionActionMapper inscriptionActionMapper;

    @Autowired
    private MissionMapper missionMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private InscriptionIndicatorRepository inscriptionIndicatorRepository;
    @Autowired
    private IndicatorRepository indicatorRepository;
    @Autowired
    private MissionRepository missionRepository;
    @Autowired
    private ActionMissionRepository actionMissionRepository;

    @Override
    public List<Inscription> findAllInscriptionsByUser(int idUser) {
        return inscriptionRepository.findAllByFkUser_Id(idUser);
    }

    @Override
    public List<MissionDto> listAllMissionsInscriptionsActionsByUser(UserDto userDto) {
        List<Inscription> inscriptions = findAllInscriptionsByUser(userDto.getId());
        List<MissionDto> list = new ArrayList<>();
        for(Inscription inscription : inscriptions) {
            MissionDto missionDto = missionMapper.toDto(inscription.getFkMission());
            missionDto.setInscriptionActions(inscriptionActionRepository.findAllByFkInscription(inscription).stream()
                    .map(inscriptionActionMapper::toDto)
                    .toList());
            list.add(missionDto);
        }
        return list;
    }

    @Override
    public InscriptionActionDto getActionInscription(int idInscription, int idAction) {
        return inscriptionActionMapper.toDto(inscriptionActionRepository.findByFkInscription_IdAndFkAction_Id(idInscription, idAction));
    }

    @Override
    public void create(int idMission, UserDto userDto) {
        Inscription inscription = new Inscription();
        inscription.setFkMission(missionRepository.findById(idMission)
                .orElseThrow(() -> new MissionException("Mission not found")));
        inscription.setFkUser(userMapper.toEntity(userDto));
        inscription.setDate(LocalDate.now());
        inscriptionRepository.save(inscription);

        List<Action> actions = actionMissionRepository.findAllByFkMission(inscription.getFkMission()).stream()
                .map(ActionMission::getFkAction)
                .toList();
        for(Action action : actions) {
            InscriptionAction inscriptionAction = new InscriptionAction();
            inscriptionAction.setFkInscription(inscription);
            inscriptionAction.setFkAction(action);
            inscriptionActionRepository.save(inscriptionAction);

            List<Indicator> indicators = indicatorRepository.findAllByFkAction_Id(action.getId());
            for(Indicator indicator : indicators) {
                InscriptionIndicator inscriptionIndicator = new InscriptionIndicator();
                inscriptionIndicator.setFkInscription(inscription);
                inscriptionIndicator.setFkAction(action);
                inscriptionIndicator.setFkIndicator(indicator);
                inscriptionIndicator.setDone(false);
                inscriptionIndicatorRepository.save(inscriptionIndicator);
            }
        }
    }

    @Override
    public void updateScore(int idInscription, int idAction) {
        InscriptionAction inscriptionAction = inscriptionActionRepository.findByFkInscription_IdAndFkAction_Id(idInscription, idAction);
        List<InscriptionIndicator> inscriptionIndicators = inscriptionIndicatorRepository.findAllByFkInscription_IdAndFkAction_Id(idInscription, idAction);
        List<Indicator> indicators = indicatorRepository.findAllByFkAction_Id(idAction);
        int score = 0;
        for(InscriptionIndicator inscriptionIndicator : inscriptionIndicators) {
            for(Indicator indicator : indicators) {
                if(inscriptionIndicator.getFkIndicator().getId().equals(indicator.getId())) {
                    score += inscriptionIndicator.getDone() ? indicator.getValueIfCheck() : indicator.getValueIfUnCheck();
                }
            }
        }
        inscriptionAction.setScore(score);
        inscriptionActionRepository.save(inscriptionAction);
    }

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
        List<InscriptionIndicator> inscriptionIndicators = inscriptionIndicatorRepository.findAllByFkInscriptionIn(inscriptions.stream()
                .map(Inscription::getId)
                .toList());
        inscriptionActionRepository.deleteAllInBatch(inscriptionActions);
        inscriptionIndicatorRepository.deleteAllInBatch(inscriptionIndicators);
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
        List<InscriptionIndicator> inscriptionIndicators = inscriptionIndicatorRepository.findAllByFkInscriptionIn(inscriptions.stream()
                .map(Inscription::getId)
                .toList());
        inscriptionIndicatorRepository.deleteAllInBatch(inscriptionIndicators);
        inscriptionActionRepository.deleteAllInBatch(inscriptionActions);
        inscriptionRepository.deleteAllInBatch(inscriptions);
    }

    @Override
    public void deleteActionInscriptions(Action action) {
        List<InscriptionAction> inscriptionActions = inscriptionActionRepository.findAllByFkAction(action);
        inscriptionActionRepository.deleteAllInBatch(inscriptionActions);
        List<InscriptionIndicator> inscriptionIndicators = inscriptionIndicatorRepository.findAllByFkAction(action);
        inscriptionIndicatorRepository.deleteAllInBatch(inscriptionIndicators);
    }

}
