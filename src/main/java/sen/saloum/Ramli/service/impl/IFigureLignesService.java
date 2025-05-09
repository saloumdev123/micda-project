package sen.saloum.Ramli.service.impl;

import sen.saloum.Ramli.dto.FigureLignesDto;

import java.util.List;

public interface IFigureLignesService {
    FigureLignesDto create(FigureLignesDto dto);
    List<FigureLignesDto> getAll();
    FigureLignesDto getById(Long id);
    FigureLignesDto update(Long id, FigureLignesDto dto);
    void delete(Long id);
}
