package sen.saloum.Ramli.mapStruct;

import org.mapstruct.Mapper;
import sen.saloum.Ramli.dto.figure.FigureRamliDto;
import sen.saloum.Ramli.models.FigureRamli;

@Mapper(componentModel = "spring")
public interface FigureRamliMapper {
    FigureRamliDto toDto(FigureRamli figureRamli);
    FigureRamli toEntity(FigureRamliDto dto);
}