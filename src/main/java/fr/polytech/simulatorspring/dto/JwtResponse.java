package fr.polytech.simulatorspring.dto;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Integer id;
	private String username;
	private final List<String> roles;

	public JwtResponse(String token, Integer id, String username, List<String> roles) {
		this.token = token;
		this.id = id;
		this.username = username;
		this.roles = roles;
	}

}
