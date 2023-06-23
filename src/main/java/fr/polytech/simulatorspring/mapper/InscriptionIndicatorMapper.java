package fr.polytech.simulatorspring.mapper;

import fr.polytech.simulatorspring.domain.InscriptionIndicator;
import fr.polytech.simulatorspring.dto.InscriptionIndicatorDto;
import org.mapstruct.*;

@Mapper
public interface InscriptionIndicatorMapper {

	@Mapping(source = "fkAction", target = "fkAction.id")
	@Mapping(source = "fkInscription", target = "fkInscription.id")
	@Mapping(source = "fkIndicator", target = "fkIndicator.id")
	InscriptionIndicator toEntity(InscriptionIndicatorDto inscriptionIndicatorDto);
	@InheritInverseConfiguration(name = "toEntity")
	InscriptionIndicatorDto toDto(InscriptionIndicator inscriptionIndicator);
	@InheritConfiguration(name = "toEntity")
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	InscriptionIndicator partialUpdate(InscriptionIndicatorDto inscriptionIndicatorDto, @MappingTarget InscriptionIndicator inscriptionIndicator);
}
