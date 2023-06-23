package fr.polytech.simulatorspring.mapper;

import fr.polytech.simulatorspring.domain.InscriptionIndicator;
import fr.polytech.simulatorspring.dto.InscriptionIndicatorDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper
public interface InscriptionIndicatorMapper {
	InscriptionIndicator toEntity(InscriptionIndicatorDto inscriptionIndicatorDto);

	InscriptionIndicatorDto toDto(InscriptionIndicator inscriptionIndicator);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	InscriptionIndicator partialUpdate(InscriptionIndicatorDto inscriptionIndicatorDto, @MappingTarget InscriptionIndicator inscriptionIndicator);
}
