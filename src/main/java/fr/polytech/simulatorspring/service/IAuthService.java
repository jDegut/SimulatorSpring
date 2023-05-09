package fr.polytech.simulatorspring.service;

import fr.polytech.simulatorspring.dto.UserDto;
import fr.polytech.simulatorspring.dto.JwtResponse;
import fr.polytech.simulatorspring.exception.AuthException;

public interface IAuthService {

	JwtResponse authenticateUser(UserDto userDto);

	JwtResponse createUser(UserDto userDto) throws AuthException;

}
