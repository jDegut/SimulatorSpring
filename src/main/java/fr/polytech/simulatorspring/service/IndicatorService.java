package fr.polytech.simulatorspring.service;

import fr.polytech.simulatorspring.domain.Action;
import fr.polytech.simulatorspring.domain.Indicator;
import fr.polytech.simulatorspring.domain.InscriptionIndicator;
import fr.polytech.simulatorspring.dto.IndicatorDto;
import fr.polytech.simulatorspring.mapper.IndicatorMapper;
import fr.polytech.simulatorspring.mapper.InscriptionIndicatorMapper;
import fr.polytech.simulatorspring.repository.IndicatorRepository;
import fr.polytech.simulatorspring.repository.InscriptionIndicatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
	public List<IndicatorDto> getAllIndicatorsActionInscription(int idInscription, int idAction) {
		List<Indicator> indicators = indicatorRepository.findAllByFkAction_Id(idAction);
		List<IndicatorDto> indicatorDtos = indicators.stream()
				.map(indicatorMapper::toDto)
				.toList();
		indicatorDtos.forEach(indicatorDto ->
				indicatorDto.setInscriptionIndicatorDto(inscriptionIndicatorMapper.toDto(
								inscriptionIndicatorRepository.findByFkInscription_IdAndFkAction_IdAndFkIndicator_Id(idInscription, idAction, indicatorDto.getId())
						)
				)
		);
		return indicatorDtos;
	}

	@Override
	public void makeDone(int idInscription, int idAction, int idIndicator) {
		InscriptionIndicator inscriptionIndicator = inscriptionIndicatorRepository.findByFkInscription_IdAndFkAction_IdAndFkIndicator_Id(idInscription, idAction, idIndicator);
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
		inscriptionIndicatorRepository.deleteAllInBatch(inscriptionIndicatorRepository.findAllByFkIndicator_Id(idIndicator));
		indicatorRepository.deleteById(idIndicator);
	}

}
