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

	private List<ActionDto> otherActions; // Field to get All other actions not in the mission
	private List<InscriptionActionDto> inscriptionActions; // Field to get All inscription to the mission

	public void setInscriptionActions(List<InscriptionActionDto> inscriptionActions) {
		this.inscriptionActions = inscriptionActions;
	}
}