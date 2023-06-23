package fr.polytech.simulatorspring.mapper;

import fr.polytech.simulatorspring.domain.Mission;
import fr.polytech.simulatorspring.dto.MissionDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper
public interface MissionMapper {
	Mission toEntity(MissionDto missionDto);

	MissionDto toDto(Mission mission);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	Mission partialUpdate(MissionDto missionDto, @MappingTarget Mission mission);
}
