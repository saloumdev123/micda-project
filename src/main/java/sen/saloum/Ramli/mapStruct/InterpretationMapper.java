package sen.saloum.Ramli.mapStruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sen.saloum.Ramli.dto.figure.InterpretationDto;
import sen.saloum.Ramli.models.Interpretation;

@Mapper(componentModel = "spring")
public interface InterpretationMapper {
    @Mapping(source = "nomFigureBase", target = "nomFigureBase")
    @Mapping(source = "typeFigure", target = "typeFigure")
    @Mapping(source = "figure.id", target = "figureId")
    InterpretationDto toDto(Interpretation entity);
    @Mapping(source = "figureId", target = "figure.id")
    Interpretation toEntity(InterpretationDto dto);
}
