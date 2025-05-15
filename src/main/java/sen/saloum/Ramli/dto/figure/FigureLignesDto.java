package sen.saloum.Ramli.dto.figure;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;



@Setter
@Getter
public class FigureLignesDto {
    private Long figureId;
    private int ligneIndex;
    private int valeur;
    private String nomLigne;
    private int point1;
    private int point2;
    private int point3;
    private int point4;

    public FigureLignesDto() {
    }

    public FigureLignesDto(Long figureId, int ligneIndex, String nomLigne, int point1, int point2, int point3, int point4) {
        this.figureId = figureId;
        this.ligneIndex = ligneIndex;
        this.nomLigne = nomLigne;
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.point4 = point4;
        calculerValeur();
    }

    public FigureLignesDto(int point1, int point2, int point3, int point4) {
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.point4 = point4;
        calculerValeur();
    }

    public void calculerValeur() {
        this.valeur = (point1 + point2 + point3 + point4) % 2;
    }

    @Override
    public String toString() {
        return "FigureLignesDto{" +
                "figureId=" + figureId +
                ", ligneIndex=" + ligneIndex +
                ", valeur=" + valeur +
                ", nomLigne='" + nomLigne + '\'' +
                ", point1=" + point1 +
                ", point2=" + point2 +
                ", point3=" + point3 +
                ", point4=" + point4 +
                '}';
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

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public String getNomLigne() {
        return nomLigne;
    }

    public void setNomLigne(String nomLigne) {
        this.nomLigne = nomLigne;
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
}
