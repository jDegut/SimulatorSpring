package fr.polytech.simulatorspring.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "action")
public class Action {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_action")
	private Action fkAction;

	@Column(name = "wording", length = 25)
	private String wording;

	@Column(name = "score_minimum")
	private Integer scoreMinimum;

}