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

    @Autowired
    private FigureRamliService figureRamliService; // Injecting the service class

    // Endpoint to retrieve all FigureRamli
    @GetMapping("/")
    public ResponseEntity<List<FigureRamliDto>> getAll() {
        List<FigureRamliDto> figureRamliDtos = figureRamliService.getAll();
        return ResponseEntity.ok(figureRamliDtos);
    }

    // Endpoint to generate FigureRamli from FigureLignesDto
    @PostMapping("/generate")
    public ResponseEntity<List<FigureRamliDto>> generateFigures(@RequestBody List<FigureLignesDto> lignesDto) {
        try {
            List<FigureRamli> figures = figureRamliService.genererFigures(lignesDto);
            List<FigureRamliDto> figuresDto = figures.stream()
                                                     .map(figureRamliService::toDto)
                                                     .collect(Collectors.toList());
            return ResponseEntity.ok(figuresDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null); // Handle errors (e.g., validation failures)
        }
    }

    // Endpoint to save a FigureRamli
    @PostMapping("/save")
    public ResponseEntity<Void> saveFigure(@RequestBody FigureRamli figure) {
        try {
            figureRamliService.save(figure); // Save the figure
            return ResponseEntity.noContent().build(); // Return a 204 No Content response
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // If the validation fails
        }
    }
}
