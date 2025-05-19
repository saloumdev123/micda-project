package sen.saloum.Ramli.dto.figure;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import sen.saloum.Ramli.enums.NomFigureBase;
import sen.saloum.Ramli.enums.TypeFigure;
@Component
public class InterpretationDto {

    private Long id;
    @NotNull(message = "meaning is required")
    private String signification;
    @NotBlank(message = "Culture is required")
    private String culture;
    private String source;
    @NotNull(message = "Figure ID is required")
    private Long figureId;
    private NomFigureBase nomFigureBase;
    private TypeFigure typeFigure;

    public InterpretationDto() {
    }

    public InterpretationDto(Long id,NomFigureBase nomFigureBase,TypeFigure typeFigure, String signification, String culture, String source, Long figureId) {
        this.id = id;
        this.signification = signification;
        this.culture = culture;
        this.source = source;
        this.figureId = figureId;
        this.nomFigureBase=nomFigureBase;
        this.typeFigure=typeFigure;
    }

    public NomFigureBase getNomFigureBase() {
        return nomFigureBase;
    }

    public void setNomFigureBase(NomFigureBase nomFigureBase) {
        this.nomFigureBase = nomFigureBase;
    }

    public TypeFigure getTypeFigure() {
        return typeFigure;
    }

    public void setTypeFigure(TypeFigure typeFigure) {
        this.typeFigure = typeFigure;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSignification() {
        return signification;
    }

    public void setSignification(String signification) {
        this.signification = signification;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Long getFigureId() {
        return figureId;
    }

    public void setFigureId(Long figureId) {
        this.figureId = figureId;
    }
}
