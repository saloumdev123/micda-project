package sen.saloum.Ramli.service.loader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;
import sen.saloum.Ramli.enums.NomFigureBase;
import sen.saloum.Ramli.enums.TypeFigure;

@Component
public class InterpretationLoader {
     private final Map<String, String> interpretationsMap = new HashMap<>();

    @PostConstruct
    public void init() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/static/interpretations_cartesiennes_enrichies.json");
        List<Map<String, Object>> list = mapper.readValue(inputStream, new TypeReference<>() {});

        for (Map<String, Object> item : list) {
            String key = item.get("nomFigureBase") + "_" + item.get("typeFigure");
            String signification = (String) item.get("signification");
            interpretationsMap.put(key, signification);
        }
    }

    public Optional<String> getInterpretation(NomFigureBase figureBase, TypeFigure typeFigure) {
        return Optional.ofNullable(interpretationsMap.get(figureBase.name() + "_" + typeFigure.name()));
    }
}
