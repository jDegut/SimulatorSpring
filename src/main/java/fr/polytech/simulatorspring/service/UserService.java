package fr.polytech.simulatorspring.service;

import fr.polytech.simulatorspring.domain.User;
import fr.polytech.simulatorspring.dto.UserDto;
import fr.polytech.simulatorspring.dto.UserUpdateRequest;
import fr.polytech.simulatorspring.exception.UserException;
import fr.polytech.simulatorspring.mapper.UserMapper;
import fr.polytech.simulatorspring.repository.UserRepository;
import fr.polytech.simulatorspring.security.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private InscriptionService inscriptionService;

	@Autowired
	private UserMapper userMapper;

	/**
	 * Get the current user (authenticated)
	 * @return
	 * @throws UserException
	 */
	@Override
	public UserDto getUser() throws UserException {
		UserDetails userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepository.findByUsername(userDetails.getUsername())
				.orElseThrow(() -> new UserException("User not found"));
		return userMapper.toDto(user);
	}

	@Override
	public UserDto getUserById(int id) throws UserException {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserException("User not found"));
		return userMapper.toDto(user);
	}

	@Override
	public UserDto updateRole(UserUpdateRequest userUpdateRequest) throws UserException {
		User user = userRepository.findById(userUpdateRequest.getId())
				.orElseThrow(() -> new UserException("User not found"));
		user.setRole(userUpdateRequest.getRole());
		return userMapper.toDto(userRepository.save(user));
	}

	/**
	 * Get all users (except connected user)
	 * @return
	 */
	@Override
	public List<UserDto> getUsers() {
		return userRepository.findAll().stream()
				.filter(user -> {
					try {
						return !user.getUsername().equals(getUser().getUsername());
					} catch (UserException e) {
						throw new RuntimeException(e);
					}
				})
				.map(userMapper::toDto)
				.toList();
	}

	@Override
	public UserDto updateUser(UserUpdateRequest userUpdateRequest) throws UserException {
		User user = userRepository.findById(userUpdateRequest.getId())
				.orElseThrow(() -> new UserException("User not found"));
		if(!Utils.comparePassword(userUpdateRequest.getOldPassword(), user.getPassword()))
			return null;
		user.setPassword(Utils.cryptPassword(userUpdateRequest.getNewPassword()));
		userRepository.save(user);
		return userMapper.toDto(user);
	}

	@Override
	public void deleteUser(Integer id) throws UserException {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserException("User not found"));
		inscriptionService.deleteUserInscriptions(id);
		userRepository.delete(user);
	}

}
