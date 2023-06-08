package fr.polytech.simulatorspring.service;

import fr.polytech.simulatorspring.dto.IndicatorDto;
import fr.polytech.simulatorspring.mapper.IndicatorMapper;
import fr.polytech.simulatorspring.repository.IndicatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndicatorService implements IIndicatorService {

    @Autowired
    private IndicatorRepository indicatorRepository;

    @Autowired
    private IndicatorMapper indicatorMapper;

    @Override
    public List<IndicatorDto> getAllIndicators() {
        return indicatorRepository.findAll().stream()
                .map(indicatorMapper::toDto)
                .toList();
    }

}
