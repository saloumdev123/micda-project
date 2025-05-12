package sen.saloum.Ramli.service;

import org.springframework.stereotype.Service;
import sen.saloum.Ramli.dto.figure.InterpretationDto;
import sen.saloum.Ramli.enums.TypeFigure;
import sen.saloum.Ramli.mapStruct.InterpretationMapper;
import sen.saloum.Ramli.models.FigureRamli;
import sen.saloum.Ramli.models.Interpretation;
import sen.saloum.Ramli.repos.InterpretationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InterpretationService {

    private final InterpretationRepository interpretationRepository;

    private final InterpretationMapper interpretationMapper;

    public InterpretationService(InterpretationRepository interpretationRepository, InterpretationMapper interpretationMapper) {
        this.interpretationRepository = interpretationRepository;
        this.interpretationMapper = interpretationMapper;
    }

    public InterpretationDto addInterpretation(InterpretationDto dto, FigureRamli fig, TypeFigure type) {
        checkIfInterpretationExists(fig, type);

        Interpretation entity = interpretationMapper.toEntity(dto);
        entity.setFigure(fig);
        entity.setTypeFigure(type);

        entity = interpretationRepository.save(entity);
        return interpretationMapper.toDto(entity);
    }


    public List<InterpretationDto> getByFigureId(Long figureId) {
        return interpretationRepository.findByFigureId(figureId)
                .stream()
                .map(interpretationMapper::toDto)
                .collect(Collectors.toList());
    }
    public void checkIfInterpretationExists(FigureRamli fig, TypeFigure type) {
        if (interpretationRepository.existsByFigureAndTypeFigure(fig, type)) {
            throw new IllegalStateException("Interprétation déjà existante pour cette figure et ce rôle.");
        }
    }
}
