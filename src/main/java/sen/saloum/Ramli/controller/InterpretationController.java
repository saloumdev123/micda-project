package sen.saloum.Ramli.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sen.saloum.Ramli.dto.figure.InterpretationDto;
import sen.saloum.Ramli.mapStruct.InterpretationMapper;
import sen.saloum.Ramli.models.FigureRamli;
import sen.saloum.Ramli.models.Interpretation;
import sen.saloum.Ramli.models.InterpretationRequest;
import sen.saloum.Ramli.service.InterpretationService;

@RestController
@RequestMapping("/api/interpretations")
public class InterpretationController {

    private final InterpretationService interpretationService;

    public InterpretationController(InterpretationService interpretationService) {
        this.interpretationService = interpretationService;
    }

    @PostMapping("/generate")
    public ResponseEntity<InterpretationDto> genererInterpretation(
            @RequestBody InterpretationRequest request
    ) {
        FigureRamli ramli = new FigureRamli();
        ramli.setId(request.getRamliId());

        Interpretation interpretation = interpretationService.genererPourFigure(ramli);

        InterpretationDto dto = InterpretationMapper.INSTANCE.toDto(interpretation); // si tu as un mapper
        return ResponseEntity.ok(dto);
    }

}
