package sen.saloum.Ramli.mapStruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import sen.saloum.Ramli.dto.figure.FigureRamliDto;
import sen.saloum.Ramli.models.FigureRamli;

@Mapper(
        componentModel = "spring",
        uses = { FigureLigneMapper.class },
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface FigureRamliMapper {
    FigureRamliMapper INSTANCE = Mappers.getMapper(FigureRamliMapper.class);

    @Mapping(target = "lignes", source = "lignes") // MapStruct va utiliser FigureLigneMapper
    FigureRamliDto toDto(FigureRamli ramli);

    @Mapping(target = "lignes", source = "lignes")
    FigureRamli toEntity(FigureRamliDto dto);

}