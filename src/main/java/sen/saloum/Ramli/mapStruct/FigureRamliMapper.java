package sen.saloum.Ramli.mapStruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sen.saloum.Ramli.dto.figure.FigureRamliDto;
import sen.saloum.Ramli.models.FigureRamli;

@Mapper(componentModel = "spring")
public interface FigureRamliMapper {


    @Mapping(source = "tirage.id", target = "tirageId")
    @Mapping(source = "nomFigureBase", target = "nomFigureBase")
    @Mapping(source = "lignes", target = "lignes")
    FigureRamliDto toDto(FigureRamli figureRamli);

    @Mapping(target = "tirage", ignore = true) // si pas nécessaire à ce moment
    FigureRamli toEntity(FigureRamliDto dto);

}