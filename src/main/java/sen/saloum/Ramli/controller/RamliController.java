package sen.saloum.Ramli.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sen.saloum.Ramli.dto.figure.FigureRamliDto;
import sen.saloum.Ramli.dto.tirage.DonneesDeBaseDto;
import sen.saloum.Ramli.dto.tirage.TirageDto;
import sen.saloum.Ramli.models.Utilisateur;
import sen.saloum.Ramli.service.RamliService;

import java.util.List;

@RestController
@RequestMapping("/api/ramli")
public class RamliController {

    private final RamliService ramliService;

    @Autowired
    public RamliController(RamliService ramliService) {
        this.ramliService = ramliService;
    }

    // Endpoint to perform a draw and generate figures
    @GetMapping("/effectuer-tirage-et-generer-figures")
    public ResponseEntity<List<FigureRamliDto>> effectuerTirageEtGenererFigures() {
        try {
            List<FigureRamliDto> figures = ramliService.effectuerTirageEtGenererFigures();
            return ResponseEntity.ok(figures);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // Internal server error if something goes wrong
        }
    }

    // Endpoint to realize a draw and return a TirageDto
    @PostMapping("/realiser-tirage")
    public ResponseEntity<TirageDto> realiserTirage(@RequestBody Utilisateur utilisateur,
                                                    @RequestBody DonneesDeBaseDto lignesDeDepart) {
        try {
            TirageDto tirageDto = ramliService.realiserTirage(utilisateur, lignesDeDepart);
            return ResponseEntity.ok(tirageDto);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // Internal server error if something goes wrong
        }
    }
}
