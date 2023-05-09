package fr.polytech.simulatorspring.mapper;

import fr.polytech.simulatorspring.domain.User;
import fr.polytech.simulatorspring.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

	User toEntity(UserDto dto);

	UserDto toDto(User entity);

}
