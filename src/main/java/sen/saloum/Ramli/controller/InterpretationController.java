package sen.saloum.Ramli.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/add")
    public ResponseEntity<InterpretationDto> addInterpretation(
            @Valid @RequestBody InterpretationDto dto,
            @RequestParam Long figureId,
            @RequestParam TypeFigure typeFigure
    ) {
        FigureRamli figure = new FigureRamli();
        figure.setId(figureId);

        InterpretationDto saved = interpretationService.addInterpretation(dto, figure, typeFigure);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/by-figure/{figureId}")
    public ResponseEntity<List<InterpretationDto>> getByFigure(@PathVariable Long figureId) {
        List<InterpretationDto> list = interpretationService.getByFigureId(figureId);
        return ResponseEntity.ok(list);
    }
}
