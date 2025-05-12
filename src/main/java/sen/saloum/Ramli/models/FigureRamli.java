package sen.saloum.Ramli.models;

import jakarta.persistence.*;
import sen.saloum.Ramli.dto.figure.FigureLignesDto;
import sen.saloum.Ramli.enums.NomFigureBase;
import sen.saloum.Ramli.enums.TypeFigure;

import java.util.List;

@Entity
public class FigureRamli {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private Integer ordre;

    private String image;

    @ManyToOne
    @JoinColumn(name = "tirage_id")
    private Tirage tirage;
    @OneToMany(mappedBy = "figure", cascade = CascadeType.ALL)
    private List<FigureLigne> lignes;
    @OneToMany(mappedBy = "figure", cascade = CascadeType.ALL)
    private List<Interpretation> interpretations;
    @Enumerated(EnumType.STRING)
    private TypeFigure typeFigure;
    @Enumerated(EnumType.STRING)
    private NomFigureBase nomFigureBase;
    private String description;

    public FigureRamli() {
    }

    public FigureRamli(Long id, String nom, Integer ordre, String image, Tirage tirage, List<FigureLigne> lignes, List<Interpretation> interpretations, TypeFigure typeFigure, NomFigureBase nomFigureBase, String description) {
        this.id = id;
        this.nom = nom;
        this.ordre = ordre;
        this.image = image;
        this.tirage = tirage;
        this.lignes = lignes;
        this.interpretations = interpretations;
        this.typeFigure = typeFigure;
        this.nomFigureBase = nomFigureBase;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getOrdre() {
        return ordre;
    }

    public void setOrdre(Integer ordre) {
        this.ordre = ordre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Tirage getTirage() {
        return tirage;
    }

    public void setTirage(Tirage tirage) {
        this.tirage = tirage;
    }

    public TypeFigure getTypeFigure() {
        return typeFigure;
    }

    public List<FigureLigne> getLignes() {
        return lignes;
    }
    public void setLignes(List<FigureLigne> lignes) {
        this.lignes = lignes;
    }

    public void setTypeFigure(TypeFigure typeFigure) {
        this.typeFigure = typeFigure;
    }

    public List<Interpretation> getInterpretations() {
        return interpretations;
    }

    public void setInterpretations(List<Interpretation> interpretations) {
        this.interpretations = interpretations;
    }

    public NomFigureBase getNomFigureBase() {
        return nomFigureBase;
    }

    public void setNomFigureBase(NomFigureBase nomFigureBase) {
        this.nomFigureBase = nomFigureBase;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
