package sen.saloum.Ramli.mapStruct;

import org.mapstruct.Mapper;
import sen.saloum.Ramli.dto.TypeFigureDto;
import sen.saloum.Ramli.enums.TypeFigure;

@Mapper(componentModel = "spring")
public interface TypeFigureMapper {
    TypeFigureDto toDto(TypeFigure entity);
    TypeFigure toEntity(TypeFigureDto dto);
}
