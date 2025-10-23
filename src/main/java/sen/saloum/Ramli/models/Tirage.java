package sen.saloum.Ramli.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import sen.saloum.Ramli.enums.NomFigureBase;
import sen.saloum.Ramli.enums.TypeFigure;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Tirage {
    @Id @GeneratedValue
    private Long id;
    private LocalDateTime dateTirage;

    @ManyToOne
    private Utilisateur utilisateur;

    @ManyToMany
    private List<FigureRamli> figures;

    @OneToMany(mappedBy = "tirage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Interpretation> interpretations = new ArrayList<>();



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

    public List<Interpretation> getInterpretations() {
        return interpretations;
    }

    public void setInterpretations(List<Interpretation> interpretations) {
        this.interpretations = interpretations;
    }
}
