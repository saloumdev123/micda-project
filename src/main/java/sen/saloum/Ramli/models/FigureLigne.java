package sen.saloum.Ramli.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

@Entity
public class FigureLigne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String valeurs;
    private int position;
    private int ligneIndex;
    private int point1;
    private int point2;
    private int point3;
    private int point4;

    @ManyToOne
    @JoinColumn(name = "figure_id")
    private FigureRamli figure;

    public FigureLigne() {
    }

    public FigureLigne(Long id, String valeurs, int position, int ligneIndex, int point1, int point2, int point3, int point4, FigureRamli figure) {
        this.id = id;
        this.valeurs = valeurs;
        this.position = position;
        this.ligneIndex = ligneIndex;
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.point4 = point4;
        this.figure = figure;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getLigneIndex() {
        return ligneIndex;
    }

    public void setLigneIndex(int ligneIndex) {
        this.ligneIndex = ligneIndex;
    }

    public String getValeurs() { return valeurs; }
    public void setValeurs(String valeurs) { this.valeurs = valeurs; }

    public int getPosition() { return position; }
    public void setPosition(int position) { this.position = position; }

    public int getPoint1() { return point1; }
    public void setPoint1(int point1) { this.point1 = point1; }

    public int getPoint2() { return point2; }
    public void setPoint2(int point2) { this.point2 = point2; }

    public int getPoint3() { return point3; }
    public void setPoint3(int point3) { this.point3 = point3; }

    public int getPoint4() { return point4; }
    public void setPoint4(int point4) { this.point4 = point4; }

    public FigureRamli getFigure() { return figure; }
    public void setFigure(FigureRamli figure) { this.figure = figure; }
}
