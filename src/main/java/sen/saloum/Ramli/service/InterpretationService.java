package sen.saloum.Ramli.service;

import org.springframework.stereotype.Service;
import sen.saloum.Ramli.dto.figure.InterpretationDto;
import sen.saloum.Ramli.enums.TypeFigure;
import sen.saloum.Ramli.mapStruct.InterpretationMapper;
import sen.saloum.Ramli.models.FigureRamli;
import sen.saloum.Ramli.models.Interpretation;
import sen.saloum.Ramli.repos.FigureRamliRepository;
import sen.saloum.Ramli.repos.InterpretationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InterpretationService {

    private final InterpretationRepository interpretationRepository;
    private final FigureRamliRepository figureRamliRepository;
    private final InterpretationMapper interpretationMapper;

    public InterpretationService(InterpretationRepository interpretationRepository, InterpretationMapper interpretationMapper, FigureRamliRepository figureRamliRepository) {
        this.interpretationRepository = interpretationRepository;
        this.figureRamliRepository = figureRamliRepository;
        this.interpretationMapper = interpretationMapper;
    }

    public InterpretationDto addInterpretation(InterpretationDto dto) {
        FigureRamli figure = figureRamliRepository.findById(dto.getFigureId())
                .orElseThrow(() -> new RuntimeException("Figure not found"));

        Interpretation entity = interpretationMapper.toEntity(dto);
        entity.setFigure(figure);

        Interpretation saved = interpretationRepository.save(entity);
        return interpretationMapper.toDto(saved);
    }


    public List<InterpretationDto> getByFigureId(Long figureId) {
        return interpretationRepository.findByFigureId(figureId)
                .stream()
                .map(interpretationMapper::toDto)
                .collect(Collectors.toList());
    }
}
