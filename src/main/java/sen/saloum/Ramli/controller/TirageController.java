package sen.saloum.Ramli.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sen.saloum.Ramli.dto.tirage.TirageDto;
import sen.saloum.Ramli.service.TirageService;

import java.util.List;

@RestController
@RequestMapping("/api/tirages")
public class TirageController {

    private final TirageService tirageService;

    public TirageController(TirageService tirageService) {
        this.tirageService = tirageService;
    }

    @PostMapping
    public ResponseEntity<TirageDto> create(@Valid @RequestBody TirageDto dto) {
        TirageDto created = tirageService.createTirage(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<TirageDto>> getAll() {
        return ResponseEntity.ok(tirageService.getAllTirages());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TirageDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(tirageService.getTirageById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tirageService.deleteTirage(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/generate")
    public ResponseEntity<List<Integer>> generateTirage() {
        return ResponseEntity.ok(tirageService.genererTirage());
    }
}
