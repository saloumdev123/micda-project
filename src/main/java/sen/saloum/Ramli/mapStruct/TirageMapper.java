package sen.saloum.Ramli.mapStruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import sen.saloum.Ramli.dto.tirage.TirageDto;
import sen.saloum.Ramli.models.Tirage;
import sen.saloum.Ramli.models.Utilisateur;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = { InterpretationMapper.class, FigureRamliMapper.class },
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface TirageMapper {
    TirageDto toDto(Tirage tirage);
    Tirage toEntity(TirageDto dto);
    List<TirageDto> toDtoList(List<Tirage> tirages);
    List<Tirage> toEntityList(List<TirageDto> dtos);
}
