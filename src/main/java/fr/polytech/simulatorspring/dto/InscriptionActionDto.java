package fr.polytech.simulatorspring.dto;

import fr.polytech.simulatorspring.domain.InscriptionAction;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link InscriptionAction}
 */
@Data
public class InscriptionActionDto implements Serializable {
	private final Integer id;
	private final Integer fkInscriptionId;
	private final LocalDate fkInscriptionDate;
	private final Integer fkActionId;
	private final String fkActionWording;
	private final Integer fkActionScoreMinimum;
	private final Integer sort;
	private final Integer score;

	private List<IndicatorDto> indicatorDtos; // Field for get All indicators for this action
}