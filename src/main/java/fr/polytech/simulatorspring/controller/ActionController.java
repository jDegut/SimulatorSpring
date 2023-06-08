package fr.polytech.simulatorspring.controller;

import fr.polytech.simulatorspring.service.ActionService;
import fr.polytech.simulatorspring.service.IndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/action")
@CrossOrigin
public class ActionController {

    @Autowired
    private ActionService actionService;

    @Autowired
    private IndicatorService indicatorService;

    @GetMapping
    public ModelAndView listAllActions() {
        return new ModelAndView("action/actions")
                .addObject("actions", actionService.getAllActions())
                .addObject("indicators", indicatorService.getAllIndicators());
    }

}
