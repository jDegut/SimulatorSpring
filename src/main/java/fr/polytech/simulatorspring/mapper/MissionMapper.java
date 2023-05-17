package fr.polytech.simulatorspring.mapper;

import fr.polytech.simulatorspring.domain.Mission;
import fr.polytech.simulatorspring.dto.MissionDto;
import org.mapstruct.*;

@Mapper
public interface MissionMapper {
	Mission toEntity(MissionDto missionDto);

	MissionDto toDto(Mission mission);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	Mission partialUpdate(MissionDto missionDto, @MappingTarget Mission mission);
}
