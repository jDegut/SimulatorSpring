package fr.polytech.simulatorspring.dto;

import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link fr.polytech.simulatorspring.domain.User} entity
 */
@Data
public class UserDto implements Serializable {
	private final Integer id;
	@NonNull
	private final String username;
	@NonNull
	private final String password;
	private final String salt;
	private final String role;
	private final String email;
	private final String surname;
	private final String forename;
}