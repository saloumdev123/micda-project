package sen.saloum.Ramli.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import sen.saloum.Ramli.dto.figure.InterpretationDto;
import sen.saloum.Ramli.enums.NomFigureBase;
import sen.saloum.Ramli.enums.TypeFigure;
import sen.saloum.Ramli.mapStruct.InterpretationMapper;
import sen.saloum.Ramli.models.FigureRamli;
import sen.saloum.Ramli.models.Interpretation;
import sen.saloum.Ramli.repos.FigureRamliRepository;
import sen.saloum.Ramli.repos.InterpretationRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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

    public InterpretationDto findByNomFigureBaseAndTypeFigure(NomFigureBase nom, TypeFigure type) {
        Interpretation interpretation = interpretationRepository
            .findByNomFigureBaseAndTypeFigure(nom, type)
            .orElseThrow(() -> new RuntimeException("Aucune interprétation trouvée"));
        return interpretationMapper.toDto(interpretation);
    }

@Transactional
public List<InterpretationDto> generateAllInterpretations() {
    List<InterpretationDto> interpretations = new ArrayList<>();

    for (NomFigureBase nomFigure : NomFigureBase.values()) {
        for (TypeFigure type : TypeFigure.values()) {
            Interpretation interpretation = new Interpretation();
            interpretation.setNomFigureBase(nomFigure);
            interpretation.setTypeFigure(type);
            interpretation.setSignification("Interprétation de " + nomFigure.getLabel() + " en " + type.getLabel());
            interpretation.setCulture("Tradition");
            interpretation.setSource("Source générée");

            // Pas de figure associée ici → setFigure(null) volontairement
            interpretationRepository.save(interpretation);

            interpretations.add(interpretationMapper.toDto(interpretation));
        }
    }

    return interpretations;
}

@Transactional
public List<InterpretationDto> importFromJson() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    InputStream inputStream = getClass().getResourceAsStream("/data/interpretations_cartesiennes.json");

    if (inputStream == null) {
        throw new RuntimeException("Fichier JSON non trouvé");
    }

    List<InterpretationDto> dtos = objectMapper.readValue(inputStream, new TypeReference<>() {});
    List<Interpretation> savedInterpretations = new ArrayList<>();

    for (InterpretationDto dto : dtos) {
        boolean exists = interpretationRepository
                .findByNomFigureBaseAndTypeFigure(dto.getNomFigureBase(), dto.getTypeFigure())
                .isPresent();

        if (!exists) {
            Interpretation interpretation = interpretationMapper.toEntity(dto);
            interpretation.setFigure(null); // Pas de figure associée
            savedInterpretations.add(interpretationRepository.save(interpretation));
        }
    }

    return savedInterpretations.stream().map(interpretationMapper::toDto).collect(Collectors.toList());
}


}
