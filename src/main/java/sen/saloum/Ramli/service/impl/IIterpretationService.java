package sen.saloum.Ramli.service.impl;

import sen.saloum.Ramli.dto.InterpretationDto;

import java.util.List;

public interface IIterpretationService {
    InterpretationDto create(InterpretationDto dto);
    List<InterpretationDto> getAll();
    InterpretationDto getById(Long id);
    InterpretationDto update(Long id, InterpretationDto dto);
    void delete(Long id);
}
