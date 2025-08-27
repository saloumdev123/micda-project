package sen.saloum.Ramli.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import sen.saloum.Ramli.enums.NomFigureBase;
import sen.saloum.Ramli.enums.TypeFigure;

import java.util.List;

@Entity
public class FigureRamli {
    @Id @GeneratedValue
    private Long id;

    private String nomFigure;
    private String description;
    private String symbolisme;

    @OneToMany(mappedBy = "figure", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private List<FigureLigne> lignes;

    @ManyToOne
    @JoinColumn(name = "tirage_id")
    private Tirage tirage;

    @OneToMany(mappedBy = "ramli", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Interpretation> interpretations;

    public FigureRamli() {
    }

    public FigureRamli(Long id, String nomFigure, String description, String symbolisme, List<FigureLigne> lignes, Tirage tirage, List<Interpretation> interpretations) {
        this.id = id;
        this.nomFigure = nomFigure;
        this.description = description;
        this.symbolisme = symbolisme;
        this.lignes = lignes;
        this.tirage = tirage;
        this.interpretations = interpretations;
    }

    public List<Interpretation> getInterpretations() {
        return interpretations;
    }

    public void setInterpretations(List<Interpretation> interpretations) {
        this.interpretations = interpretations;
    }

    public Tirage getTirage() {
        return tirage;
    }

    public void setTirage(Tirage tirage) {
        this.tirage = tirage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomFigure() {
        return nomFigure;
    }

    public void setNomFigure(String nomFigure) {
        this.nomFigure = nomFigure;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSymbolisme() {
        return symbolisme;
    }

    public void setSymbolisme(String symbolisme) {
        this.symbolisme = symbolisme;
    }

    public List<FigureLigne> getLignes() {
        return lignes;
    }

    public void setLignes(List<FigureLigne> lignes) {
        this.lignes = lignes;
    }
}
