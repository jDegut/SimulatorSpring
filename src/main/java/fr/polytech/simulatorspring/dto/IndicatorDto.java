package fr.polytech.simulatorspring.dto;

import fr.polytech.simulatorspring.domain.Indicator;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Indicator}
 */
@Data
public class IndicatorDto implements Serializable {
    private final Integer id;
    private final Integer fkActionId;
    private final String fkActionWording;
    private final Integer fkActionScoreMinimum;
    private final String wording;
    private final Integer valueIfCheck;
    private final Integer valueIfUnCheck;
}