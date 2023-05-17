package fr.polytech.simulatorspring.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "username", nullable = false, length = 100)
	private String username;

	@Column(name = "password", nullable = false, length = 100)
	private String password;

	@Column(name = "role", nullable = false, length = 100)
	private String role;

	@Column(name = "email")
	private String email;

	@Column(name = "surname", length = 50)
	private String surname;

	@Column(name = "forename", length = 50)
	private String forename;

}