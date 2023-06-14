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

	@Column(name = "fk_inscription", nullable = false)
	private Integer fkInscription;

	@Column(name = "fk_action", nullable = false)
	private Integer fkAction;

	@Column(name = "fk_indicator", nullable = false)
	private Integer fkIndicator;

	@Column(name = "done")
	private Boolean done;

}