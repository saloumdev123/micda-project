package sen.saloum.Ramli.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sen.saloum.Ramli.dto.figure.FigureLignesDto;
import sen.saloum.Ramli.dto.figure.FigureRamliDto;
import sen.saloum.Ramli.models.FigureRamli;
import sen.saloum.Ramli.service.FigureRamliService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/figure-ramli")
public class FigureRamliController {

    private final FigureRamliService figureRamliService;

    public FigureRamliController(FigureRamliService figureRamliService) {
        this.figureRamliService = figureRamliService;
    }
    @GetMapping("/tirage/{tirageId}")
    public ResponseEntity<List<FigureRamliDto>> getFiguresByTirageIdWithDetails(@PathVariable Long tirageId) {
        List<FigureRamliDto> figures = figureRamliService.findByTirageIdWithLinesAndInterpretations(tirageId);
        return ResponseEntity.ok(figures);
    }
    @GetMapping("/all")
    public ResponseEntity<List<FigureRamliDto>> getAll() {
        List<FigureRamliDto> figureRamliDtos = figureRamliService.getAll();
        return ResponseEntity.ok(figureRamliDtos);
    }

    @PostMapping("/generate")
    public ResponseEntity<List<FigureRamliDto>> generateFigures(@RequestBody List<FigureLignesDto> lignesDto) {
        try {
            List<FigureRamliDto> figuresDto = figureRamliService.genererEtRetournerDto(lignesDto);
            return ResponseEntity.ok(figuresDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping(value = "/save", consumes = {"application/json", "application/json;charset=UTF-8"})
    public ResponseEntity<Void> saveFigure(@RequestBody FigureRamli figure) {
        try {
            figureRamliService.save(figure);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
