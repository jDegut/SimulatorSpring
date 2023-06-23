package fr.polytech.simulatorspring.controller;

import fr.polytech.simulatorspring.dto.IndicatorDto;
import fr.polytech.simulatorspring.service.IIndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/indicator")
public class IndicatorController {
    @Autowired
    private IIndicatorService indicatorService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> createIndicator(IndicatorDto indicatorDto) {
        try {
            indicatorService.createIndicator(indicatorDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Indicateur créé");
    }
}
