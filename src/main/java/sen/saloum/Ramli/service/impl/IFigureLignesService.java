package sen.saloum.Ramli.service.impl;

import sen.saloum.Ramli.dto.figure.FigureLignesDto;
import sen.saloum.Ramli.models.FigureLigneId;

import java.util.List;

public interface IFigureLignesService {
    FigureLignesDto create(FigureLignesDto dto);
    List<FigureLignesDto> getAll();
    FigureLignesDto getById(FigureLigneId id);
    FigureLignesDto update(FigureLigneId id, FigureLignesDto dto);
    void delete(FigureLigneId id);
}
