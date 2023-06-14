package fr.polytech.simulatorspring.service;

import fr.polytech.simulatorspring.domain.Action;
import fr.polytech.simulatorspring.domain.Indicator;
import fr.polytech.simulatorspring.domain.Inscription;
import fr.polytech.simulatorspring.domain.InscriptionIndicator;
import fr.polytech.simulatorspring.dto.IndicatorDto;
import fr.polytech.simulatorspring.dto.InscriptionIndicatorDto;
import fr.polytech.simulatorspring.mapper.IndicatorMapper;
import fr.polytech.simulatorspring.mapper.InscriptionIndicatorMapper;
import fr.polytech.simulatorspring.repository.IndicatorRepository;
import fr.polytech.simulatorspring.repository.InscriptionIndicatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IndicatorService implements IIndicatorService {

	@Autowired
	private IndicatorRepository indicatorRepository;

	@Autowired
	private InscriptionIndicatorRepository inscriptionIndicatorRepository;

	@Autowired
	private IndicatorMapper indicatorMapper;

	@Autowired
	private InscriptionIndicatorMapper inscriptionIndicatorMapper;

	@Override
	public List<IndicatorDto> getAllIndicators() {
		return indicatorRepository.findAll().stream()
				.map(indicatorMapper::toDto)
				.toList();
	}

	@Override
	public List<IndicatorDto> getAllIndicatorsAction(int idAction) {
		return indicatorRepository.findAllByFkAction_Id(idAction).stream()
				.map(indicatorMapper::toDto)
				.toList();
	}

	@Override
	public Map<IndicatorDto, InscriptionIndicatorDto> getAllIndicatorsActionInscription(int idInscription, int idAction) {
		List<Indicator> indicators = indicatorRepository.findAllByFkAction_Id(idAction);
		Map<IndicatorDto, InscriptionIndicatorDto> map = new HashMap<>();
		for(Indicator indicator : indicators) {
			map.put(indicatorMapper.toDto(indicator),
					inscriptionIndicatorMapper.toDto(
							inscriptionIndicatorRepository.findByFkInscriptionAndFkActionAndFkIndicator(idInscription, idAction, indicator.getId())
					));
		}
		return map;
	}

	public void makeDone(int idInscription, int idAction, int idIndicator) {
		InscriptionIndicator inscriptionIndicator = inscriptionIndicatorRepository.findByFkInscriptionAndFkActionAndFkIndicator(idInscription, idAction, idIndicator);
		inscriptionIndicator.setDone(!inscriptionIndicator.getDone());
		inscriptionIndicatorRepository.save(inscriptionIndicator);

	}

	@Override
	public void createIndicator(IndicatorDto indicatorDto) {
		indicatorRepository.save(indicatorMapper.toEntity(indicatorDto));
	}

	@Override
	public void deleteAllActionIndicator(Action action) {
		List<Indicator> indicators = indicatorRepository.findAllByFkAction(action);
		indicatorRepository.deleteAllInBatch(indicators);
	}

	@Override
	public void deleteIndicator(int idIndicator) {
		indicatorRepository.deleteById(idIndicator);
	}
}
