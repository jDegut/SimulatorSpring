package fr.polytech.simulatorspring.service;

import fr.polytech.simulatorspring.domain.Utilisateur;
import fr.polytech.simulatorspring.repository.UtilisateurRepository;
import fr.polytech.simulatorspring.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UtilisateurRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utilisateur user = userRepository.findByNomUtil(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		return new UserDetailsImpl(user);
	}
}
