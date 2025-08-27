package sen.saloum.Ramli.models;

import jakarta.persistence.*;
import sen.saloum.Ramli.enums.NomFigureBase;
import sen.saloum.Ramli.enums.TypeFigure;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
public class Tirage {
    @Id @GeneratedValue
    private Long id;
    private LocalDateTime dateTirage;

    @ManyToOne
    private Utilisateur utilisateur;

    @ManyToMany
    private List<FigureRamli> figures;

    @OneToOne(mappedBy = "tirage", cascade = CascadeType.ALL)
    private Interpretation interpretation;

    public Tirage() {
    }

    public Tirage(Long id, LocalDateTime dateTirage, Utilisateur utilisateur, List<FigureRamli> figures, Interpretation interpretation) {
        this.id = id;
        this.dateTirage = dateTirage;
        this.utilisateur = utilisateur;
        this.figures = figures;
        this.interpretation = interpretation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTirage() {
        return dateTirage;
    }

    public void setDateTirage(LocalDateTime dateTirage) {
        this.dateTirage = dateTirage;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<FigureRamli> getFigures() {
        return figures;
    }

    public void setFigures(List<FigureRamli> figures) {
        this.figures = figures;
    }

    public Interpretation getInterpretation() {
        return interpretation;
    }

    public void setInterpretation(Interpretation interpretation) {
        this.interpretation = interpretation;
    }
}
