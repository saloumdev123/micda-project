package sen.saloum.Ramli.dto.figure;

import lombok.Data;

@Data
public class SimpleInterpretationDto {
    private String nomFigure;
    private String typeFigure;
    private String interpretation;

    public String getNomFigure() {
        return nomFigure;
    }

    public void setNomFigure(String nomFigure) {
        this.nomFigure = nomFigure;
    }

    public String getTypeFigure() {
        return typeFigure;
    }

    public void setTypeFigure(String typeFigure) {
        this.typeFigure = typeFigure;
    }

    public String getInterpretation() {
        return interpretation;
    }

    public void setInterpretation(String interpretation) {
        this.interpretation = interpretation;
    }
}
