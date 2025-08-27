package sen.saloum.Ramli.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sen.saloum.Ramli.dto.figure.FigureRamliDto;
import sen.saloum.Ramli.service.FigureRamliService;

import java.util.List;

@RestController
@RequestMapping("/api/ramlis")
public class RamliController {

    private final FigureRamliService ramliService;

    public RamliController(FigureRamliService ramliService) {
        this.ramliService = ramliService;
    }


    // 🔹 Créer un Ramli
    @PostMapping
    public ResponseEntity<FigureRamliDto> create(@RequestBody FigureRamliDto ramliDto) {
        return ResponseEntity.ok(ramliService.create(ramliDto));
    }

    // 🔹 Lister tous les Ramlis
    @GetMapping
    public ResponseEntity<List<FigureRamliDto>> getAll() {
        return ResponseEntity.ok(ramliService.findAll());
    }

    // 🔹 Récupérer un Ramli par ID
    @GetMapping("/{id}")
    public ResponseEntity<FigureRamliDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ramliService.findById(id));
    }

    // 🔹 Mettre à jour un Ramli existant
    @PutMapping("/{id}")
    public ResponseEntity<FigureRamliDto> update(@PathVariable Long id, @RequestBody FigureRamliDto ramliDto) {
        FigureRamliDto updated = ramliService.update(id, ramliDto);
        return ResponseEntity.ok(updated);
    }

    // 🔹 Supprimer un Ramli
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ramliService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 🔹 Récupérer une figure aléatoire
    @GetMapping("/random")
    public ResponseEntity<FigureRamliDto> getRandom() {
        return ResponseEntity.ok(ramliService.getRandom());
    }

}
