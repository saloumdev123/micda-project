package sen.saloum.Ramli.models;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import sen.saloum.Ramli.dto.figure.SimpleInterpretationDto;
import sen.saloum.Ramli.enums.NomFigureBase;
import sen.saloum.Ramli.enums.TypeFigure;
import sen.saloum.Ramli.repos.InterpretationRepository;

public class InterpretationImporter implements CommandLineRunner {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final InterpretationRepository interpretationRepository;

    public InterpretationImporter(InterpretationRepository repo) {
        this.interpretationRepository = repo;
    }

    @Override
    public void run(String... args) throws Exception {
        File jsonFile = new File("src/main/resources/static/interpretations_cartesiennes_enrichies.json");
        List<SimpleInterpretationDto> dtos = Arrays.asList(
            objectMapper.readValue(jsonFile, SimpleInterpretationDto[].class)
        );
        for (SimpleInterpretationDto dto : dtos) {
            Interpretation i = new Interpretation();
            i.setNomFigureBase(NomFigureBase.valueOf(dto.getNomFigure()));
            i.setTypeFigure(TypeFigure.valueOf(dto.getTypeFigure()));
            i.setSignification(dto.getInterpretation());
            i.setCulture("générique");
            interpretationRepository.save(i);
        }
    }
    
}
