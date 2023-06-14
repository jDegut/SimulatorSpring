package fr.polytech.simulatorspring.dto;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link fr.polytech.simulatorspring.domain.InscriptionIndicator}
 */
@Data
public class InscriptionIndicatorDto implements Serializable {
	private final Integer id;
	private final Integer fkInscription;
	private final Integer fkAction;
	private final Integer fkIndicator;
	private final Boolean done;
}