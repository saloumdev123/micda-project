package sen.saloum.Ramli.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sen.saloum.Ramli.dto.tirage.TirageDto;
import sen.saloum.Ramli.service.TirageService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/tirages")
public class TirageController {

    private final TirageService tirageService;

    public TirageController(TirageService tirageService) {
        this.tirageService = tirageService;
    }

    @PostMapping("/user/{userId}/generate")
    public ResponseEntity<TirageDto> generateTirage(@PathVariable("userId") Long userId) {
        System.out.println("🎯 TirageController détecté pour l'utilisateur ID : " + userId);
        return ResponseEntity.ok(tirageService.effectuerTirage(userId));
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TirageDto>> getTiragesByUser(@PathVariable Long userId) {
        List<TirageDto> tirages = tirageService.getTiragesByUser(userId);
        return ResponseEntity.ok(tirages);
    }

    /**
     * 🔹 3. Récupérer un tirage par son ID
     */
    @GetMapping("/{tirageId}")
    public ResponseEntity<TirageDto> getTirageById(@PathVariable Long tirageId) {
        TirageDto tirage = tirageService.getTirageById(tirageId);
        return ResponseEntity.ok(tirage);
    }

    /**
     * 🔹 4. Récupérer tous les tirages
     */
    @GetMapping("/all")
    public ResponseEntity<List<TirageDto>> getAllTirages() {
        List<TirageDto> tirages = tirageService.getAllTirages();
        return ResponseEntity.ok(tirages);
    }

    // === Endpoint pour générer une liste de points ===
    @GetMapping("/generer/{nb}")
    public ResponseEntity<List<Integer>> genererPoints(@PathVariable int nb) {
        List<Integer> points = genererPointsIntern(nb);
        return ResponseEntity.ok(points);
    }

    // === Méthode interne pour générer les points ===
    private List<Integer> genererPointsIntern(int nb) {
        Random rand = new Random();
        List<Integer> points = new ArrayList<>();
        for (int i = 0; i < nb; i++) {
            points.add(rand.nextBoolean() ? 1 : 0); // 1 = •, 0 = ○
        }
        return points;
    }
}
