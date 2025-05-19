package sen.saloum.Ramli.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sen.saloum.Ramli.service.FigureRamliService;

@RestController
@RequestMapping("/admin")
//@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final FigureRamliService figureRamliService;

    public AdminController(FigureRamliService figureRamliService) {
        this.figureRamliService = figureRamliService;
    }

    @PostMapping("/reload-interpretations")
    public ResponseEntity<String> reloadInterpretations() {
        figureRamliService.reloadInterpretationMap();
        return ResponseEntity.ok("Interprétations rechargées avec succès.");
    }
}
