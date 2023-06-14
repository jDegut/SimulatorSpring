package fr.polytech.simulatorspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link fr.polytech.simulatorspring.domain.Action} entity
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
}