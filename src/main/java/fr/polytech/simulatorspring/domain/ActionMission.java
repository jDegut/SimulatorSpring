package fr.polytech.simulatorspring.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "action__mission")
public class ActionMission {
	@EmbeddedId
	private ActionMissionId id;

	@MapsId("fkAction")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "fk_action", nullable = false)
	private Action fkAction;

	@MapsId("fkMission")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "fk_mission", nullable = false)
	private Mission fkMission;

}