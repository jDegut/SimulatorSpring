package fr.polytech.simulatorspring.service;

import fr.polytech.simulatorspring.dto.UserDto;
import fr.polytech.simulatorspring.dto.UserUpdateRequest;
import fr.polytech.simulatorspring.exception.UserException;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface IUserService {

	UserDto getUser() throws UserException;

	UserDto getUserById(int id) throws UserException;

	UserDto updateRole(UserUpdateRequest userUpdateRequest) throws UserException;

	List<UserDto> getUsers();

	UserDto updateUser(UserUpdateRequest userUpdateRequest) throws UserException;

	void deleteUser(Integer id) throws UserException;

}
