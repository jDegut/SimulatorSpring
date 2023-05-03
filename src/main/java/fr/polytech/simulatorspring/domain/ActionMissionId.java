package fr.polytech.simulatorspring.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class ActionMissionId implements Serializable {
	private static final long serialVersionUID = 2957348254620086819L;
	@Column(name = "fk_action", nullable = false)
	private Integer fkAction;

	@Column(name = "fk_mission", nullable = false)
	private Integer fkMission;

}