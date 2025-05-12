package sen.saloum.Ramli.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sen.saloum.Ramli.dto.figure.FigureLignesDto;
import sen.saloum.Ramli.mapStruct.FigureLignesMapper;
import sen.saloum.Ramli.models.FigureLigne;
import sen.saloum.Ramli.service.FigureLigneService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/figure-lignes")
public class FigureLigneController {

    private final FigureLigneService figureLigneService;
    private final FigureLignesMapper figureLignesMapper;

    public FigureLigneController(FigureLigneService figureLigneService, FigureLignesMapper figureLignesMapper) {
        this.figureLigneService = figureLigneService;
        this.figureLignesMapper = figureLignesMapper;
    }

    @PostMapping
    public ResponseEntity<FigureLignesDto> create(@Valid @RequestBody FigureLignesDto dto) {
        FigureLignesDto created = figureLigneService.create(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/by-figure/{figureId}")
    public ResponseEntity<List<FigureLignesDto>> getByFigureId(@PathVariable Long figureId) {
        return ResponseEntity.ok(figureLigneService.getByFigureId(figureId));
    }

    @PostMapping("/saveAll")
    public ResponseEntity<?> saveAll(@RequestBody List<FigureLignesDto> lignesDto) {
        List<FigureLigne> lignesEntity = lignesDto.stream()
                .map(figureLignesMapper::toEntity)
                .collect(Collectors.toList());

        figureLigneService.saveAll(lignesEntity);

        return ResponseEntity.ok("Lignes enregistrées avec succès.");
    }
    @PostMapping("/generate/{figureId}")
    public ResponseEntity<List<FigureLignesDto>> generateLignes(
            @RequestBody List<Integer> tirage,
            @PathVariable Long figureId) {
        try {
            // Call the service method to generate lines
            List<FigureLignesDto> lignesDto = figureLigneService.genererLignesDepuisTirage(tirage, figureId);
            return ResponseEntity.ok(lignesDto);
        } catch (IllegalArgumentException e) {
            // Handle invalid input (tirage size not multiple of 4)
            return ResponseEntity.badRequest().body(null); // You can add more specific error messages if needed
        }
    }
}
