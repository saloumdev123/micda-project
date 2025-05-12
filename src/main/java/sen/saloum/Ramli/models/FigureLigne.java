package sen.saloum.Ramli.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
public class FigureLigne {

    @EmbeddedId
    private FigureLigneId id;

    @ManyToOne
    @MapsId("figureId")
    @JoinColumn(name = "figure_id")
    private FigureRamli figure;
    private String nomLigne;
    private int valeur;
    private int point1;
    private int point2;
    private int point3;
    private int point4;

    public FigureLigne() {
    }

    public FigureLigne(FigureLigneId id, FigureRamli figure, String nomLigne, int valeur, int point1, int point2, int point3, int point4) {
        this.id = id;
        this.figure = figure;
        this.nomLigne = nomLigne;
        this.valeur = valeur;
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.point4 = point4;
    }

    public int getPoint1() {
        return point1;
    }

    public void setPoint1(int point1) {
        this.point1 = point1;
    }

    public int getPoint2() {
        return point2;
    }

    public void setPoint2(int point2) {
        this.point2 = point2;
    }

    public int getPoint3() {
        return point3;
    }

    public void setPoint3(int point3) {
        this.point3 = point3;
    }

    public int getPoint4() {
        return point4;
    }

    public void setPoint4(int point4) {
        this.point4 = point4;
    }

    public FigureRamli getFigure() {
        return figure;
    }

    public void setFigure(FigureRamli figure) {
        this.figure = figure;
    }

    public int getLigneIndex() {
        return id != null ? id.getLigneIndex() : 0;
    }

    public void setLigneIndex(int ligneIndex) {
        if (id == null) {
            id = new FigureLigneId();
        }
        id.setLigneIndex(ligneIndex);
    }

    public String getNomLigne() {
        return nomLigne;
    }

    public void setNomLigne(String nomLigne) {
        this.nomLigne = nomLigne;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public FigureLigneId getId() {
        return id;
    }

    public void setId(FigureLigneId id) {
        this.id = id;
    }
}
