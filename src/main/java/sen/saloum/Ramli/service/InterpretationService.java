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
import sen.saloum.Ramli.models.Tirage;
import sen.saloum.Ramli.repos.FigureRamliRepository;
import sen.saloum.Ramli.repos.InterpretationRepository;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InterpretationService {
    private final Map<String, String> interpretations = Map.of(
            "Al-Lahjah", "Votre tirage révèle une période propice à la communication...",
            "Al-Jamiah", "L’union fait la force. Votre tirage suggère...",
            "Al-Nasrah", "La victoire est à portée de main !"
    );

    private final InterpretationRepository interpretationRepository;
    private final FigureRamliRepository figureRamliRepository;

    public InterpretationService(InterpretationRepository interpretationRepository, FigureRamliRepository figureRamliRepository) {
        this.interpretationRepository = interpretationRepository;
        this.figureRamliRepository = figureRamliRepository;
    }

    public Interpretation genererPourFigureId(Long ramliId) {
        FigureRamli entity = figureRamliRepository.findById(ramliId)
                .orElseThrow(() -> new RuntimeException("FigureRamli introuvable avec ID: " + ramliId));

        Interpretation interpretation = new Interpretation();
        interpretation.setRamli(entity);
        interpretation.setTexteInterpretation("Interpretation généré avec succès...");
        interpretation.setDateInterpretation(LocalDateTime.now());

        return interpretationRepository.save(interpretation);
    }

    public Interpretation genererPourFigure(FigureRamli figure) {
        if (figure == null || figure.getId() == null) {
            throw new IllegalArgumentException("La figure Ramli ne peut pas être nulle.");
        }

        Interpretation interpretation = new Interpretation();
        interpretation.setRamli(figure);
        interpretation.setTexteInterpretation(
                interpretations.getOrDefault(
                        figure.getNomFigure(),
                        "Aucune interprétation disponible pour cette figure."
                )
        );
        interpretation.setDateInterpretation(LocalDateTime.now());

        return interpretationRepository.save(interpretation);
    }

    public Interpretation genererPourFigureEtTirage(FigureRamli figure, Tirage tirage) {
        if (figure == null || figure.getId() == null) {
            throw new IllegalArgumentException("La figure Ramli ne peut pas être nulle.");
        }

        Interpretation interpretation = new Interpretation();
        interpretation.setRamli(figure);
        interpretation.setTirage(tirage); // ✅ relation établie
        interpretation.setTexteInterpretation(
                interpretations.getOrDefault(
                        figure.getNomFigure(),
                        "Aucune interprétation disponible pour cette figure."
                )
        );
        interpretation.setDateInterpretation(LocalDateTime.now());

        return interpretationRepository.save(interpretation);
    }

    public List<Interpretation> getAllInterpretations() {
        return interpretationRepository.findAll();
    }

}
