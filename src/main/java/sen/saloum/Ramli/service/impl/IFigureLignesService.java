package sen.saloum.Ramli.service.impl;

import sen.saloum.Ramli.dto.figure.FigureLignesDto;

import java.util.List;

public interface IFigureLignesService {
    FigureLignesDto create(FigureLignesDto dto);
    List<FigureLignesDto> getAll();
}
