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

    @GetMapping("/create")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> createMission(){
        try{
            return ResponseEntity.ok(actionService.getAllActions());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> createMission(@ModelAttribute MissionDto missionDto, @RequestParam("actionList") List<Integer> actionIds ){
        try{
            missionService.createMission(missionDto, actionIds);
            return ResponseEntity.ok("Mission créée");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> modifyMission(@PathVariable int id){
        try{
            List<Object> objects = new ArrayList<>();
            MissionDto missionDto = missionService.findMissionById(id);
            objects.add(missionDto);
            objects.add(actionService.getAllOtherActionByMission(missionDto));
            return ResponseEntity.ok(objects);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{id}/action/add")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> addActionToMission(@PathVariable int id, @ModelAttribute ActionDto actionDto){
        try{
            missionService.addToMission(id, actionDto);
            return ResponseEntity.ok("Action ajoutée");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}/remove/{actionId}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> removeActionToMission(@PathVariable int id, @PathVariable int actionId, @ModelAttribute ActionDto actionDto){
        try{
            missionService.removeAction(id, actionId);
            return ResponseEntity.ok("Action supprimée");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> deleteMission(@PathVariable int id){
        try{
            missionService.deleteMission(id);
            return ResponseEntity.ok("Mission supprimée");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
