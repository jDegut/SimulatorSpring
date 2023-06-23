package fr.polytech.simulatorspring.dto;

import fr.polytech.simulatorspring.domain.Action;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link Action} entity
 */
@Data
@AllArgsConstructor
public class ActionDto implements Serializable {
	private final Integer id;
	private Integer previousActionId;
	private String previousActionWording;
	private Integer previousActionScoreMinimum;
	private final String wording;
	private final Integer scoreMinimum;
	private List<IndicatorDto> indicators;

	public void setIndicators(List<IndicatorDto> indicators) {
		this.indicators = indicators;
	}

	public int getId() {
		return id;
	}
}