package fr.polytech.simulatorspring.mapper;

import fr.polytech.simulatorspring.domain.Action;
import fr.polytech.simulatorspring.dto.ActionDto;
import org.mapstruct.*;

@Mapper
public interface ActionMapper {
    @Mapping(source = "previousActionScoreMinimum", target = "fkAction.scoreMinimum")
    @Mapping(source = "previousActionWording", target = "fkAction.wording")
    @Mapping(source = "previousActionId", target = "fkAction.id")
    Action toEntity(ActionDto actionDto);

    @InheritInverseConfiguration(name = "toEntity")
    ActionDto toDto(Action action);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Action partialUpdate(ActionDto actionDto, @MappingTarget Action action);
}
