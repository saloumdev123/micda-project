package sen.saloum.Ramli.controller;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sen.saloum.Ramli.dto.user.UtilisateurDto;
import sen.saloum.Ramli.service.UtilisateurService;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PostMapping("/save")
    public ResponseEntity<UtilisateurDto> create(@Valid @RequestBody UtilisateurDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(utilisateurService.create(dto));
    }
    @GetMapping("/search") 
    public ResponseEntity<UtilisateurDto> searchByUsername(@RequestParam String username) {
        UtilisateurDto dto = utilisateurService.searchByUsername(username);
        return ResponseEntity.ok(dto);
    }


    @GetMapping("/all")
    public ResponseEntity<List<UtilisateurDto>> getAll() {
        List<UtilisateurDto> utilisateurs = utilisateurService.getAll();
        return ResponseEntity.ok(utilisateurs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(utilisateurService.getById(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<UtilisateurDto> update(@PathVariable Long id, @RequestBody UtilisateurDto dto) {
        return ResponseEntity.ok(utilisateurService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        utilisateurService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
