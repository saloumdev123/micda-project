package sen.saloum.Ramli.mapStruct;

import org.mapstruct.Mapper;
import sen.saloum.Ramli.dto.FigureLignesDto;
import sen.saloum.Ramli.models.FigureLigne;

@Mapper(componentModel = "spring")
public interface FigureLignesMapper {
    FigureLignesDto toDto(FigureLigne entity);
    FigureLigne toEntity(FigureLignesDto dto);
}
