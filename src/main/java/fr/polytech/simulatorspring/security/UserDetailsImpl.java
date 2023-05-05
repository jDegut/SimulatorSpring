package fr.polytech.simulatorspring.security;

import fr.polytech.simulatorspring.domain.Utilisateur;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {
	private final int numUtil;
	private final String nomUtil;
	private final String motPasse;
	private final String salt;
	private final String role;
	private final String email;
	private final String surename;
	private final String forename;

	public UserDetailsImpl(Utilisateur utilisateur) {
		this.numUtil = utilisateur.getId();
		this.nomUtil = utilisateur.getNomUtil();
		this.motPasse = utilisateur.getMotPasse();
		this.salt = utilisateur.getSalt();
		this.role = utilisateur.getRole();
		this.email = utilisateur.getEmail();
		this.surename = utilisateur.getSurname();
		this.forename = utilisateur.getForename();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(() -> role);
	}

	@Override
	public String getPassword() {
		return motPasse;
	}

	@Override
	public String getUsername() {
		return nomUtil;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
