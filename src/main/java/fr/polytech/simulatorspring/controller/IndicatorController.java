package fr.polytech.simulatorspring.controller;

import fr.polytech.simulatorspring.dto.IndicatorDto;
import fr.polytech.simulatorspring.service.IIndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/indicator")
public class IndicatorController {
    @Autowired
    private IIndicatorService indicatorService;

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> createIndicator(@RequestBody IndicatorDto indicatorDto) {
        try {
            indicatorService.createIndicator(indicatorDto);
            return ResponseEntity.ok("Indicateur créé");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
