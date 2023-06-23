package fr.polytech.simulatorspring.controller;

import fr.polytech.simulatorspring.dto.ActionDto;
import fr.polytech.simulatorspring.dto.IndicatorDto;
import fr.polytech.simulatorspring.service.IActionService;
import fr.polytech.simulatorspring.service.IIndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/action")
public class ActionController {
    @Autowired
    private IActionService actionService;

    @Autowired
    private IIndicatorService indicatorService;

    @GetMapping
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> listActions() {
        Collection<ActionDto> actions;
        try {
            actions = actionService.getAllActions();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(actions);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> getAction(@PathVariable int id) {
        try {
            return ResponseEntity.ok(actionService.getAction(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/add")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> createAction(@RequestBody ActionDto actionDto) {
        try {
            actionService.createAction(actionDto);
            return ResponseEntity.ok("Action créée");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> deleteAction(@PathVariable int id) {
        try{
            actionService.deleteAction(id);
            return ResponseEntity.ok("Action supprimée");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
