package fr.polytech.simulatorspring.service;

import fr.polytech.simulatorspring.dto.UserDto;
import fr.polytech.simulatorspring.exception.UserException;

public interface IUserService {

	UserDto updateUser(UserDto userDto) throws UserException;

	void deleteUser(Integer id) throws UserException;

}
