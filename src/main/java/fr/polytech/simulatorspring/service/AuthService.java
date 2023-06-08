package fr.polytech.simulatorspring.service;

import fr.polytech.simulatorspring.domain.User;
import fr.polytech.simulatorspring.dto.UserDto;
import fr.polytech.simulatorspring.exception.AuthException;
import fr.polytech.simulatorspring.mapper.UserMapper;
import fr.polytech.simulatorspring.repository.UserRepository;
import fr.polytech.simulatorspring.security.jwt.JwtTokenUtils;
import fr.polytech.simulatorspring.dto.JwtResponse;
import fr.polytech.simulatorspring.security.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService implements IAuthService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private JwtTokenUtils jwtTokenUtils;

	/**
	 * Check if the user is authenticated
	 * @return
	 */
	@Override
	public boolean isAuth() {
		return SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
				&& !SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser");
	}

	/**
	 * Authenticate a user via JwtResponse (dto)
	 * @param userDto
	 * @return
	 */
	@Override
	public JwtResponse authenticateUser(UserDto userDto) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtTokenUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.toList();

		return new JwtResponse(jwt,
				userDetails.getId(),
				userDetails.getUsername(),
				roles);
	}

	/**
	 * Create a new user and authenticate it
	 * @param userDto
	 * @return
	 * @throws AuthException
	 */
	@Override
	public JwtResponse createUser(UserDto userDto) throws AuthException {
		if (userDto.getEmail() == null || userDto.getForename() == null || userDto.getSurname() == null)
			throw new AuthException("Missing fields");
		if (userRepository.existsByUsername(userDto.getUsername()) || userRepository.existsByEmail(userDto.getEmail()))
			throw new AuthException("Username or email already exists");

		User user = userMapper.toEntity(userDto);
		user.setPassword(Utils.cryptPassword(userDto.getPassword()));
		user.setRole("learner");
		userRepository.save(user);

		return authenticateUser(userDto);
	}

}
