package fr.polytech.simulatorspring.service;

import fr.polytech.simulatorspring.domain.User;
import fr.polytech.simulatorspring.dto.UserDto;
import fr.polytech.simulatorspring.exception.UserException;
import fr.polytech.simulatorspring.mapper.UserMapper;
import fr.polytech.simulatorspring.repository.UserRepository;
import fr.polytech.simulatorspring.security.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;

	@Override
	public UserDto updateUser(UserDto userDto) throws UserException {

		UserDetails userAuthenticated = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (!userAuthenticated.getUsername().equals(userDto.getUsername()))
			throw new UserException("You can't update this user");

		User user = userRepository.findById(userDto.getId())
				.orElseThrow(() -> new UserException("User not found"));
		user.setPassword(Utils.cryptPassword(userDto.getPassword()));
		user.setSalt(userDto.getSalt());
		user.setRole(userDto.getRole());
		user.setEmail(userDto.getEmail());
		user.setForename(userDto.getForename());
		user.setSurname(userDto.getSurname());
		userRepository.save(user);
		return userMapper.toDto(user);
	}

	@Override
	public void deleteUser(Integer id) throws UserException {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserException("User not found"));
		userRepository.delete(user);
	}

}
