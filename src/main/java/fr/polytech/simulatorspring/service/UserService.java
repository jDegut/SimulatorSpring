package fr.polytech.simulatorspring.service;

import fr.polytech.simulatorspring.domain.User;
import fr.polytech.simulatorspring.dto.UserDto;
import fr.polytech.simulatorspring.dto.UserUpdateRequest;
import fr.polytech.simulatorspring.exception.UserException;
import fr.polytech.simulatorspring.mapper.UserMapper;
import fr.polytech.simulatorspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;

	@Override
	public UserDto getUser(UserDetails userDetails) throws UserException {
		User user = userRepository.findByUsername(userDetails.getUsername())
				.orElseThrow(() -> new UserException("User not found"));
		return userMapper.toDto(user);
	}

	@Override
	public UserDto updateUser(UserUpdateRequest userUpdateRequest) throws UserException {
		User user = userRepository.findById(userUpdateRequest.getId())
				.orElseThrow(() -> new UserException("User not found"));
		System.out.println(Utils.comparePassword(userUpdateRequest.getOldPassword(), user.getPassword()));
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
		userRepository.delete(user);
	}

}
