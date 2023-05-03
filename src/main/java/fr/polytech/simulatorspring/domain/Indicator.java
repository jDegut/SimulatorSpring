package fr.polytech.simulatorspring.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "indicator")
public class Indicator {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "fk_action", nullable = false)
	private Action fkAction;

	@Column(name = "wording", length = 50)
	private String wording;

	@Column(name = "valueIfCheck")
	private Integer valueIfCheck;

	@Column(name = "valueIfUnCheck")
	private Integer valueIfUnCheck;

}