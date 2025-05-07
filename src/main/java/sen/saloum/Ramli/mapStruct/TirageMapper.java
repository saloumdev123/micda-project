package sen.saloum.Ramli.mapStruct;

import org.mapstruct.Mapper;
import sen.saloum.Ramli.dto.FigureRamliDto;
import sen.saloum.Ramli.dto.TirageDto;
import sen.saloum.Ramli.models.FigureRamli;
import sen.saloum.Ramli.models.Tirage;

@Mapper(componentModel = "spring")
public interface TirageMapper {
    TirageDto toDto(Tirage tirage);
    Tirage toEntity(TirageDto tirageDto);
}


