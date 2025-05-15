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
    @PostMapping("/aleatoire/{utilisateurId}")
    public ResponseEntity<TirageDto> creerTirageAleatoire(@PathVariable Long utilisateurId,@RequestBody TirageDto dto) {
        TirageDto tirageDto = tirageService.creerTirageAleatoire(utilisateurId,dto);
        return ResponseEntity.ok(tirageDto);
    }
    @PostMapping("/generate")
    public ResponseEntity<List<Integer>> generateTirage() {
        return ResponseEntity.ok(tirageService.genererTirageValeurs());
    }
}
