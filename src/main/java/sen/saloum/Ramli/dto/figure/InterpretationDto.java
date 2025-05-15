package sen.saloum.Ramli.dto.figure;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import sen.saloum.Ramli.enums.NomFigureBase;
import sen.saloum.Ramli.enums.TypeFigure;
@Setter
@Getter
@Component
public class InterpretationDto {
    private Long id;
    private String signification;
    @NotBlank(message = "Culture is required")
    private String culture;
    private String source;
    @NotNull(message = "Figure ID is required")
    private Long figureId;

    public InterpretationDto() {
    }

    public InterpretationDto(Long id, String signification, String culture, String source, Long figureId) {
        this.id = id;
        this.signification = signification;
        this.culture = culture;
        this.source = source;
        this.figureId = figureId;
    }

}
