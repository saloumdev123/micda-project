package sen.saloum.Ramli.mapStruct;

import org.mapstruct.Mapper;
import sen.saloum.Ramli.dto.InterpretationDto;
import sen.saloum.Ramli.models.Interpretation;

@Mapper(componentModel = "spring")
public interface InterpretationMapper {
    InterpretationDto toDto(Interpretation entity);
    Interpretation toEntity(InterpretationDto dto);
}
