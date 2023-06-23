package fr.polytech.simulatorspring.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link fr.polytech.simulatorspring.domain.Mission} entity
 */
@Data
public class MissionDto implements Serializable {
	private final Integer id;
	private final String wording;
	private List<ActionDto> actions;

	private List<ActionDto> otherActions; // Field for get All other actions not in the mission
}