package sen.saloum.Ramli.models;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FigureLigneId implements Serializable {

    private Long figureId;
    private int ligneIndex;

    public FigureLigneId() {
    }

    public FigureLigneId(Long figureId, int ligneIndex) {
        this.figureId = figureId;
        this.ligneIndex = ligneIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FigureLigneId)) return false;
        FigureLigneId that = (FigureLigneId) o;
        return ligneIndex == that.ligneIndex &&
                Objects.equals(figureId, that.figureId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(figureId, ligneIndex);
    }

    public Long getFigureId() {
        return figureId;
    }

    public void setFigureId(Long figureId) {
     this.figureId = figureId;
    }

    public int getLigneIndex() {
        return ligneIndex;
    }

    public void setLigneIndex(int ligneIndex) {
        this.ligneIndex = ligneIndex;
    }
}
