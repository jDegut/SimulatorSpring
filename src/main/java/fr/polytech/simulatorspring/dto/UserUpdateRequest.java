package fr.polytech.simulatorspring.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserUpdateRequest implements Serializable {
	private final int id;
	private final String oldPassword;
	private final String newPassword;
	private final String newPassword2;
}
