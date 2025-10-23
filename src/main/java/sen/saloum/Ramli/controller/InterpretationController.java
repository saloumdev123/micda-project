package sen.saloum.Ramli.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sen.saloum.Ramli.dto.figure.InterpretationDto;
import sen.saloum.Ramli.mapStruct.InterpretationMapper;
import sen.saloum.Ramli.models.FigureRamli;
import sen.saloum.Ramli.models.Interpretation;
import sen.saloum.Ramli.models.InterpretationRequest;
import sen.saloum.Ramli.service.InterpretationService;

import java.util.List;

@RestController
@RequestMapping("/api/interpretations")
public class InterpretationController {

    private final InterpretationService interpretationService;

    public InterpretationController(InterpretationService interpretationService) {
        this.interpretationService = interpretationService;
    }

    @PostMapping("/generate")
    public ResponseEntity<?> genererInterpretation(@RequestBody InterpretationRequest request) {
        if (request.getRamliId() == null) {
            return ResponseEntity.badRequest().body("Le champ ramliId est requis.");
        }

        Interpretation interpretation = interpretationService.genererPourFigureId(request.getRamliId());
        return ResponseEntity.ok(InterpretationMapper.INSTANCE.toDto(interpretation));
    }


    @GetMapping
    public ResponseEntity<List<Interpretation>> getAllInterpretations() {
        List<Interpretation> interpretations = interpretationService.getAllInterpretations();
        return ResponseEntity.ok(interpretations);
    }

    @PostMapping("/generate/{figureId}")
    public ResponseEntity<Interpretation> generateInterpretation(@PathVariable Long figureId) {
        Interpretation interpretation = interpretationService.genererPourFigureId(figureId);
        return ResponseEntity.ok(interpretation);
    }

}
