package sen.saloum.Ramli.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public InterpretationDto addInterpretation(InterpretationDto dto) {
        if (dto.getFigureId() == null) {
            throw new IllegalArgumentException("Figure ID cannot be null");
        }

        FigureRamli figure = figureRamliRepository.findById(dto.getFigureId())
                .orElseThrow(() -> new RuntimeException("Figure not found"));

        Interpretation entity = interpretationMapper.toEntity(dto);
        entity.setFigure(figure);
        entity.setNomFigureBase(figure.getNomFigureBase());
        entity.setTypeFigure(figure.getTypeFigure());

        // 🔐 Nettoyage des champs pour éviter les erreurs et doublons
        if (entity.getCulture() != null) {
            entity.setCulture(entity.getCulture().trim());
        }
        if (entity.getSource() != null) {
            entity.setSource(entity.getSource().trim());
        }
        if (entity.getSignification() != null) {
            entity.setSignification(entity.getSignification().trim());
        }



        Interpretation saved = interpretationRepository.save(entity);
        return interpretationMapper.toDto(saved);
    }



    public List<InterpretationDto> getByFigureId(Long figureId) {
        return interpretationRepository.findByFigureId(figureId)
                .stream()
                .map(interpretationMapper::toDto)
                .collect(Collectors.toList());
    }
    @Transactional
    public InterpretationDto updateInterpretation(Long id, InterpretationDto dto) {
        Interpretation existing = interpretationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Interpretation not found"));

        existing.setSignification(dto.getSignification());
        existing.setCulture(dto.getCulture());
        existing.setSource(dto.getSource());

        // On garde le nom/type en cohérence avec la figure
        FigureRamli figure = figureRamliRepository.findById(dto.getFigureId())
                .orElseThrow(() -> new RuntimeException("Figure not found"));
        existing.setFigure(figure);
        existing.setNomFigureBase(figure.getNomFigureBase());
        existing.setTypeFigure(figure.getTypeFigure());

        Interpretation updated = interpretationRepository.save(existing);
        return interpretationMapper.toDto(updated);
    }
    public List<InterpretationDto> getAllInterpretations() {
        return interpretationRepository.findAll()
                .stream()
                .map(interpretationMapper::toDto)
                .collect(Collectors.toList());
    }
    public void deleteInterpretation(Long id) {
        if (!interpretationRepository.existsById(id)) {
            throw new RuntimeException("Interpretation not found");
        }
        interpretationRepository.deleteById(id);
    }
    public List<InterpretationDto> getByTypeFigure(TypeFigure type) {
        List<Interpretation> interpretations = interpretationRepository.findByTypeFigure(type);
        return interpretations.stream()
                .map(interpretationMapper::toDto)
                .collect(Collectors.toList());
    }



}
