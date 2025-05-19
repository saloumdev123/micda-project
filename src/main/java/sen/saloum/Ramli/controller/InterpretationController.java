package sen.saloum.Ramli.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sen.saloum.Ramli.dto.figure.InterpretationDto;
import sen.saloum.Ramli.enums.TypeFigure;
import sen.saloum.Ramli.models.FigureRamli;
import sen.saloum.Ramli.service.InterpretationService;

import java.util.List;

@RestController
@RequestMapping("/api/interpretations")
public class InterpretationController {

    private final InterpretationService interpretationService;

    public InterpretationController(InterpretationService interpretationService) {
        this.interpretationService = interpretationService;
    }

    // Créer une nouvelle interprétation
    @PostMapping
    public ResponseEntity<InterpretationDto> createInterpretation(@RequestBody InterpretationDto dto) {
        InterpretationDto created = interpretationService.addInterpretation(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // Récupérer toutes les interprétations
    @GetMapping
    public ResponseEntity<List<InterpretationDto>> getAllInterpretations() {
        List<InterpretationDto> list = interpretationService.getAllInterpretations();
        return ResponseEntity.ok(list);
    }

    // Récupérer interprétations par figureId
    @GetMapping("/figure/{figureId}")
    public ResponseEntity<List<InterpretationDto>> getByFigureId(@PathVariable Long figureId) {
        List<InterpretationDto> list = interpretationService.getByFigureId(figureId);
        return ResponseEntity.ok(list);
    }

    // Récupérer interprétations par TypeFigure (ex: TYPE1, TYPE2...)
    @GetMapping("/type/{type}")
    public ResponseEntity<List<InterpretationDto>> getByTypeFigure(@PathVariable TypeFigure type) {
        List<InterpretationDto> list = interpretationService.getByTypeFigure(type);
        return ResponseEntity.ok(list);
    }

    // Mettre à jour une interprétation
    @PutMapping("/{id}")
    public ResponseEntity<InterpretationDto> updateInterpretation(@PathVariable Long id, @RequestBody InterpretationDto dto) {
        InterpretationDto updated = interpretationService.updateInterpretation(id, dto);
        return ResponseEntity.ok(updated);
    }

    // Supprimer une interprétation
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInterpretation(@PathVariable Long id) {
        interpretationService.deleteInterpretation(id);
        return ResponseEntity.noContent().build();
    }
}
