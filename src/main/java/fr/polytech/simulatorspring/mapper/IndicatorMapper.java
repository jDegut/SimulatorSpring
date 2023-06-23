package fr.polytech.simulatorspring.mapper;

import fr.polytech.simulatorspring.domain.Indicator;
import fr.polytech.simulatorspring.dto.IndicatorDto;
import org.mapstruct.*;

@Mapper
public interface IndicatorMapper {
    @Mapping(source = "fkActionScoreMinimum", target = "fkAction.scoreMinimum")
    @Mapping(source = "fkActionWording", target = "fkAction.wording")
    @Mapping(source = "fkActionId", target = "fkAction.id")
    Indicator toEntity(IndicatorDto indicatorDto);

    @InheritInverseConfiguration(name = "toEntity")
    IndicatorDto toDto(Indicator indicator);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Indicator partialUpdate(IndicatorDto indicatorDto, @MappingTarget Indicator indicator);
}
