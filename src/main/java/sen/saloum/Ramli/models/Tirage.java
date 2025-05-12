package sen.saloum.Ramli.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
public class Tirage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomTirage;
    private OffsetDateTime dateTirage;
    @Column(columnDefinition = "TEXT")
    private String interpretation;
    private String question;
    private String figureResultats;
    private String nomConsultant;
    private String valeurs;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "tirage", cascade = CascadeType.ALL)
    private List<FigureRamli> figures;
    public Tirage() {
    }

    public Tirage(Long id, String nomTirage, OffsetDateTime dateTirage, String interpretation, String question, String figureResultats, String nomConsultant, String valeurs, Utilisateur utilisateur, List<FigureRamli> figures) {
        this.id = id;
        this.nomTirage = nomTirage;
        this.dateTirage = dateTirage;
        this.interpretation = interpretation;
        this.question = question;
        this.figureResultats = figureResultats;
        this.nomConsultant = nomConsultant;
        this.valeurs = valeurs;
        this.utilisateur = utilisateur;
        this.figures = figures;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomTirage() {
        return nomTirage;
    }

    public void setNomTirage(String nomTirage) {
        this.nomTirage = nomTirage;
    }

    public OffsetDateTime getDateTirage() {
        return dateTirage;
    }

    public void setDateTirage(OffsetDateTime dateTirage) {
        this.dateTirage = dateTirage;
    }

    public String getInterpretation() {
        return interpretation;
    }

    public void setInterpretation(String interpretation) {
        this.interpretation = interpretation;
    }

    public String getNomConsultant() {
        return nomConsultant;
    }

    public void setNomConsultant(String nomConsultant) {
        this.nomConsultant = nomConsultant;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getFigureResultats() {
        return figureResultats;
    }

    public void setFigureResultats(String figureResultats) {
        this.figureResultats = figureResultats;
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

    public String getValeurs() {
        return valeurs;
    }

    public void setValeurs(String valeurs) {
        this.valeurs = valeurs;
    }

}
