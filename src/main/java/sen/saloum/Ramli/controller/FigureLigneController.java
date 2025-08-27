package sen.saloum.Ramli.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sen.saloum.Ramli.dto.figure.FigureLignesDto;
import sen.saloum.Ramli.service.FigureLigneService;

import java.util.List;

@RestController
@RequestMapping("/api/figure-lignes")
public class FigureLigneController {
    private final FigureLigneService figureLigneService;

    public FigureLigneController(FigureLigneService service) {
        this.figureLigneService = service;
    }

    @PostMapping("/create/{figureId}")
    public ResponseEntity<FigureLignesDto> create(
            @Valid @RequestBody FigureLignesDto dto,
            @PathVariable Long figureId) {
        return ResponseEntity.ok(figureLigneService.create(dto, figureId));
    }

    @GetMapping("/by-figure/{figureId}")
    public ResponseEntity<List<FigureLignesDto>> getByFigureId(@PathVariable Long figureId) {
        return ResponseEntity.ok(figureLigneService.getByFigureId(figureId));
    }



    @PutMapping("/{id}")
    public ResponseEntity<FigureLignesDto> update(@PathVariable Long id,
                                                  @RequestBody FigureLignesDto dto) {
        return ResponseEntity.ok(figureLigneService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        figureLigneService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
