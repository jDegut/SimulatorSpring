package fr.polytech.simulatorspring.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "inscription__indicator")
public class InscriptionIndicator {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "fk_inscription", nullable = false)
	private Inscription fkInscription;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "fk_action", nullable = false)
	private Action fkAction;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "fk_indicator", nullable = false)
	private Indicator fkIndicator;

	@Column(name = "done")
	private Boolean done;

}