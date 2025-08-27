package sen.saloum.Ramli.mapStruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import sen.saloum.Ramli.dto.figure.FigureLignesDto;
import sen.saloum.Ramli.models.FigureLigne;

@Mapper(componentModel = "spring")
public interface FigureLigneMapper {

    FigureLignesDto toDto(FigureLigne entity);
    FigureLigne toEntity(FigureLignesDto dto);
}
