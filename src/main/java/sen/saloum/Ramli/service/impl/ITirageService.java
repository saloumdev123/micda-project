package sen.saloum.Ramli.service.impl;

import sen.saloum.Ramli.dto.TirageDto;

import java.util.List;

public interface ITirageService {
    TirageDto create(TirageDto dto);
    TirageDto getById(Long id);
    List<TirageDto> getAll();
    TirageDto update(Long id, TirageDto dto);
    void delete(Long id);
}
