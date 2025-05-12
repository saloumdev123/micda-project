package sen.saloum.Ramli.mapStruct;

import org.mapstruct.Mapper;
import sen.saloum.Ramli.dto.tirage.TirageDto;
import sen.saloum.Ramli.models.Tirage;

@Mapper(componentModel = "spring")
public interface TirageMapper {
    TirageDto toDto(Tirage tirage);
    Tirage toEntity(TirageDto tirageDto);
}


