package sen.saloum.Ramli.mapStruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import sen.saloum.Ramli.dto.figure.InterpretationDto;
import sen.saloum.Ramli.models.Interpretation;

@Mapper(componentModel = "spring")
public interface InterpretationMapper {
    InterpretationMapper INSTANCE = Mappers.getMapper(InterpretationMapper.class);

    @Mapping(source = "ramli.id", target = "ramliId")
    @Mapping(source = "tirage.id", target = "tirageId")
    InterpretationDto toDto(Interpretation interpretation);

    @Mapping(source = "ramliId", target = "ramli.id")
    @Mapping(source = "tirageId", target = "tirage.id")
    Interpretation toEntity(InterpretationDto dto);
}
