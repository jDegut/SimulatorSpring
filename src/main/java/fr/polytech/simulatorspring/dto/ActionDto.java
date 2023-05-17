package fr.polytech.simulatorspring.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link fr.polytech.simulatorspring.domain.Action} entity
 */
@Data
public class ActionDto implements Serializable {
	private final Integer id;
	private final Integer previousActionId;
	private final String previousActionWording;
	private final Integer previousActionScoreMinimum;
	private final String wording;
	private final Integer scoreMinimum;
}