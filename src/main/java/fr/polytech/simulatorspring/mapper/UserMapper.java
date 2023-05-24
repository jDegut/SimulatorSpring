package fr.polytech.simulatorspring.mapper;

import fr.polytech.simulatorspring.domain.User;
import fr.polytech.simulatorspring.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

	User toEntity(UserDto dto);

	@Mapping(target = "forename", expression = "java(entity.getForename().toUpperCase())")
	UserDto toDto(User entity);

}
