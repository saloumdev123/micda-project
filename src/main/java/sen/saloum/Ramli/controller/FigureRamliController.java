package sen.saloum.Ramli.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sen.saloum.Ramli.dto.figure.FigureLignesDto;
import sen.saloum.Ramli.dto.figure.FigureRamliDto;
import sen.saloum.Ramli.enums.NomFigureBase;
import sen.saloum.Ramli.enums.TypeFigure;
import sen.saloum.Ramli.models.FigureRamli;
import sen.saloum.Ramli.service.FigureRamliService;

import java.util.List;

@RestController
@RequestMapping("/api/figure-ramli")
public class FigureRamliController {

    private final FigureRamliService figureRamliService;

    public FigureRamliController(FigureRamliService figureRamliService) {
        this.figureRamliService = figureRamliService;
    }
    @GetMapping("/tirage/{tirageId}")
    public ResponseEntity<?> getFiguresByTirageIdWithDetails(@PathVariable Long tirageId) {
        try {
            List<FigureRamliDto> figures = figureRamliService.findByTirageIdWithLinesAndInterpretations(tirageId);
            return ResponseEntity.ok(figures);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ID de tirage invalide ou autre erreur : " + e.getMessage());
        }
    }
    @PostMapping("/admin/reload-interpretations")
    public ResponseEntity<String> reloadInterpretationsCache() {
        try {
            figureRamliService.reloadInterpretationMap();
            return ResponseEntity.ok("Cache des interprétations rechargé avec succès.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors du rechargement du cache : " + e.getMessage());
        }
    }
    @GetMapping("/interpretation")
    public ResponseEntity<String> getInterpretation(@RequestParam NomFigureBase nomFigureBase,
                                                    @RequestParam TypeFigure typeFigure) {
        try {
            String interpretation = figureRamliService.getInterpretation(nomFigureBase, typeFigure);
            return interpretation != null ?
                    ResponseEntity.ok(interpretation) :
                    ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de la récupération de l'interprétation : " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<FigureRamliDto>> getAll() {
        List<FigureRamliDto> figureRamliDtos = figureRamliService.getAll();
        return ResponseEntity.ok(figureRamliDtos);
    }

    @PostMapping("/generate")
    public ResponseEntity<List<FigureRamliDto>> generateFigures(
            @Valid @RequestBody List<FigureLignesDto> lignesDto,
            @RequestParam("tirageId") Long tirageId) {
        try {
            List<FigureRamliDto> figuresDto = figureRamliService.genererEtRetournerDto(lignesDto, tirageId);
            return ResponseEntity.ok(figuresDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @PostMapping(value = "/save", consumes = {"application/json", "application/json;charset=UTF-8"})
    public ResponseEntity<Void> saveFigure(@Valid @RequestBody FigureRamli figure) {
        try {
            figureRamliService.save(figure);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
