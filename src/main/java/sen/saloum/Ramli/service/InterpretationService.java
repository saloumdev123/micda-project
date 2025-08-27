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

    public Interpretation genererPourFigure(FigureRamli figure) {
        Interpretation i = new Interpretation();
        i.setTexteInterpretation(interpretations.getOrDefault(
                figure.getNomFigure(),
                "Interprétation en cours de développement."
        ));
        i.setFigure(figure);
        i.setDateInterpretation(LocalDateTime.now());
        return i;
    }
}
