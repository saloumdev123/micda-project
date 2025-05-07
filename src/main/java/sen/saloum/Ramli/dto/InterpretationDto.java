package sen.saloum.Ramli.dto;

import lombok.Data;

@Data
public class InterpretationDto {
    private Long id;
    private String nomFigure;
    private String signification;
    private String culture;
    private String source;
    private Long figureId;
}
