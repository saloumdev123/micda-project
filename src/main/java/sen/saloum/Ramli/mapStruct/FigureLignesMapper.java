package sen.saloum.Ramli.mapStruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sen.saloum.Ramli.dto.figure.FigureLignesDto;
import sen.saloum.Ramli.models.FigureLigne;

@Mapper(componentModel = "spring")
public interface FigureLignesMapper {
    @Mapping(source = "id.figureId", target = "figureId")
    @Mapping(source = "id.ligneIndex", target = "ligneIndex")
    FigureLignesDto toDto(FigureLigne entity);

    @Mapping(target = "id.figureId", source = "figureId")
    @Mapping(target = "id.ligneIndex", source = "ligneIndex")
    FigureLigne toEntity(FigureLignesDto dto);
}
