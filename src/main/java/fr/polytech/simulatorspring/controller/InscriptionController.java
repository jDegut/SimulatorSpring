package fr.polytech.simulatorspring.controller;


import fr.polytech.simulatorspring.dto.UserDto;
import fr.polytech.simulatorspring.service.IIndicatorService;
import fr.polytech.simulatorspring.service.IInscriptionService;
import fr.polytech.simulatorspring.service.IMissionService;
import fr.polytech.simulatorspring.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/inscription")
public class InscriptionController {

    private static final Logger logger = LoggerFactory.getLogger(InscriptionController.class);

    @Autowired
    private IInscriptionService inscriptionService;
    @Autowired
    private IMissionService missionService;
    @Autowired
    private IIndicatorService indicatorService;
    @Autowired
    private IUserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('learner')")
    public ResponseEntity<?> listInscriptions() {
        UserDto userDto;
        try {
            userDto = userService.getUser();
            return ResponseEntity.ok(inscriptionService.listAllMissionsInscriptionsActionsByUser(userDto));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{idInscription}/action/{idAction}")
    @PreAuthorize("hasAuthority('learner')")
    public ResponseEntity<?> getUserIndicators(@PathVariable int idInscription, @PathVariable int idAction) {
        try {
            List<Object> retour = new ArrayList<>();
            retour.add(inscriptionService.getActionInscription(idInscription, idAction));
            retour.add(indicatorService.getAllIndicatorsActionInscription(idInscription, idAction));
            return ResponseEntity.ok(retour);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{idInscription}/action/{idAction}/indicator/{idIndicator}")
    @PreAuthorize("hasAuthority('learner')")
    public ResponseEntity<?> applyIndicatorDone(@PathVariable int idInscription, @PathVariable int idAction, @PathVariable int idIndicator) {
        try {
            indicatorService.makeDone(idInscription, idAction, idIndicator);
            inscriptionService.updateScore(idInscription, idAction);
            return ResponseEntity.ok("Action marquée à done");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('learner')")
    public ResponseEntity<?> addInscription(){
        try{
            UserDto userDto = userService.getUser();
            return ResponseEntity.ok(missionService.getAllMissionsNotInscribed(userDto));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/add/{idMission}")
    @PreAuthorize("hasAuthority('learner')")
    public ResponseEntity<?> sign(@PathVariable int idMission){
        try{
            UserDto userDto = userService.getUser();
            inscriptionService.create(idMission, userDto);
            return ResponseEntity.ok("Inscription ajoutée");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
