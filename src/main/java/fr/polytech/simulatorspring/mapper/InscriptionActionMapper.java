package fr.polytech.simulatorspring.mapper;

import fr.polytech.simulatorspring.domain.InscriptionAction;
import fr.polytech.simulatorspring.dto.InscriptionActionDto;
import org.mapstruct.*;

@Mapper
public interface InscriptionActionMapper {
	@Mapping(source = "fkActionScoreMinimum", target = "fkAction.scoreMinimum")
	@Mapping(source = "fkActionWording", target = "fkAction.wording")
	@Mapping(source = "fkActionId", target = "fkAction.id")
	@Mapping(source = "fkInscriptionDate", target = "fkInscription.date")
	@Mapping(source = "fkInscriptionId", target = "fkInscription.id")
	abstract InscriptionAction toEntity(InscriptionActionDto inscriptionActionDto);

	@InheritInverseConfiguration(name = "toEntity")
	abstract InscriptionActionDto toDto(InscriptionAction inscriptionAction);

	@InheritConfiguration(name = "toEntity")
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	abstract InscriptionAction partialUpdate(InscriptionActionDto inscriptionActionDto, @MappingTarget InscriptionAction inscriptionAction);
}
