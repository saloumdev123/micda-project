package sen.saloum.Ramli.controller;

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

    @PostMapping("/user/{userId}/generate")
    public ResponseEntity<TirageDto> generateTirage(@PathVariable Long userId) {
        return ResponseEntity.ok(tirageService.effectuerTirage(userId));
    }
}
