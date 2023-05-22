package fr.polytech.simulatorspring.service;

import fr.polytech.simulatorspring.dto.UserDto;
import fr.polytech.simulatorspring.dto.UserUpdateRequest;
import fr.polytech.simulatorspring.exception.UserException;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService {

	UserDto getUser(UserDetails userDetails) throws UserException;

	UserDto updateUser(UserUpdateRequest userUpdateRequest) throws UserException;

	void deleteUser(Integer id) throws UserException;

}
