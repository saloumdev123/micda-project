package sen.saloum.Ramli.dto.figure;


import sen.saloum.Ramli.enums.NomFigureBase;
import sen.saloum.Ramli.enums.TypeFigure;

public class FigureRamliDto {
    private Long id;
    private int ordre;
    private String nom;
    private String image;
    private Long tirageId;
    private NomFigureBase nomFigureBase;
    private TypeFigure typeFigure;

    public FigureRamliDto() {
    }

    public FigureRamliDto(Long id, int ordre, String nom, String image, Long tirageId, NomFigureBase nomFigureBase, TypeFigure typeFigure) {
        this.id = id;
        this.ordre = ordre;
        this.nom = nom;
        this.image = image;
        this.tirageId = tirageId;
        this.nomFigureBase = nomFigureBase;
        this.typeFigure = typeFigure;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOrdre() {
        return ordre;
    }

    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getTirageId() {
        return tirageId;
    }

    public void setTirageId(Long tirageId) {
        this.tirageId = tirageId;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
