package fr.polytech.simulatorspring.controller;

import fr.polytech.simulatorspring.dto.ActionDto;
import fr.polytech.simulatorspring.dto.MissionDto;
import fr.polytech.simulatorspring.service.IActionService;
import fr.polytech.simulatorspring.service.IMissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/mission")
public class MissionController {
    @Autowired
    private IMissionService missionService;

    @Autowired
    private IActionService actionService;

    @GetMapping
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> listMissions(){
        try{
            return ResponseEntity.ok(missionService.findAllMissions());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /* Faire une requete get sur les actions
    @GetMapping("/create")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> createMission(){
        try{
            return ResponseEntity.ok(actionService.getAllActions());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }*/

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> createMission(@RequestBody MissionDto missionDto, @RequestParam("actionList") List<Integer> actionIds ){
        try{
            missionService.createMission(missionDto, actionIds);
            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> getMission(@PathVariable int id){
        try{
            MissionDto missionDto = missionService.findMissionById(id);
            missionDto.setOtherActions(actionService.getAllOtherActionByMission(missionDto));
            return ResponseEntity.ok(missionDto);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> addActionToMission(@PathVariable int id, @RequestBody ActionDto actionDto){
        if(actionDto == null){
            return ResponseEntity.badRequest().body("Action non valide");
        }
        try{
            missionService.addToMission(id, actionDto);
            return ResponseEntity.ok("Action ajoutée");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}/{actionId}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> removeActionToMission(@PathVariable int id, @PathVariable int actionId){
        try{
            missionService.removeAction(id, actionId);
            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> deleteMission(@PathVariable int id){
        try{
            missionService.deleteMission(id);
            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
