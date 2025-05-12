package sen.saloum.Ramli.dto.figure;

import sen.saloum.Ramli.enums.NomFigureBase;
import sen.saloum.Ramli.enums.TypeFigure;

public class InterpretationDto {
    private Long id;
    private NomFigureBase nomFigureBase;
    private TypeFigure typeFigure;
    private String signification;
    private String culture;
    private String source;
    private Long figureId;

    public InterpretationDto() {
    }

    public InterpretationDto(Long id, NomFigureBase nomFigureBase, TypeFigure typeFigure, String signification, String culture, String source, Long figureId) {
        this.id = id;
        this.nomFigureBase = nomFigureBase;
        this.typeFigure = typeFigure;
        this.signification = signification;
        this.culture = culture;
        this.source = source;
        this.figureId = figureId;
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
}
