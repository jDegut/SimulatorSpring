package fr.polytech.simulatorspring.service;

import fr.polytech.simulatorspring.domain.Action;
import fr.polytech.simulatorspring.dto.IndicatorDto;
import fr.polytech.simulatorspring.dto.InscriptionIndicatorDto;

import java.util.List;
import java.util.Map;

public interface IIndicatorService {
	List<IndicatorDto> getAllIndicators();

	List<IndicatorDto> getAllIndicatorsAction(int idAction);

	Map<IndicatorDto, InscriptionIndicatorDto> getAllIndicatorsActionInscription(int idInscription, int idAction);

	void makeDone(int idInscription, int idAction, int idIndicator);

	void createIndicator(IndicatorDto indicatorDto);

	void deleteAllActionIndicator(Action action);

	void deleteIndicator(int idIndicator);
}