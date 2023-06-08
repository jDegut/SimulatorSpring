package fr.polytech.simulatorspring.service;

import fr.polytech.simulatorspring.dto.IndicatorDto;

import java.util.List;

public interface IIndicatorService {
    List<IndicatorDto> getAllIndicators();
}
