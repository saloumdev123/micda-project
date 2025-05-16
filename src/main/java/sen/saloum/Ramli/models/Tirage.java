package sen.saloum.Ramli.models;

import jakarta.persistence.*;
import lombok.*;
import sen.saloum.Ramli.enums.NomFigureBase;
import sen.saloum.Ramli.enums.TypeFigure;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
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
    @Enumerated(EnumType.STRING)
    private TypeFigure typeFigure;
    @Enumerated(EnumType.STRING)
    private NomFigureBase nomFigureBase;
    private Long version;
    @OneToMany(mappedBy = "tirage", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<FigureRamli> figures = new ArrayList<>();

    public Tirage() {
    }
    public Tirage(Long id,Long version, String nomTirage,NomFigureBase nomFigureBase,TypeFigure typeFigure, OffsetDateTime dateTirage, String interpretation,
                  String question, String figureResultats, String nomConsultant, String valeurs,
                  Utilisateur utilisateur) {
        this.id = id;
        this.nomTirage = nomTirage;
        this.dateTirage = dateTirage;
        this.interpretation = interpretation;
        this.question = question;
        this.figureResultats = figureResultats;
        this.nomConsultant = nomConsultant;
        this.valeurs = valeurs;
        this.utilisateur = utilisateur;
        this.nomFigureBase=nomFigureBase;
        this.typeFigure=typeFigure;
        this.version = version;
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

    public String getNomConsultant() {
        return nomConsultant;
    }

    public void setNomConsultant(String nomConsultant) {
        this.nomConsultant = nomConsultant;
    }

    public String getValeurs() {
        return valeurs;
    }

    public void setValeurs(String valeurs) {
        this.valeurs = valeurs;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public TypeFigure getTypeFigure() {
        return typeFigure;
    }

    public void setTypeFigure(TypeFigure typeFigure) {
        this.typeFigure = typeFigure;
    }

    public NomFigureBase getNomFigureBase() {
        return nomFigureBase;
    }

    public void setNomFigureBase(NomFigureBase nomFigureBase) {
        this.nomFigureBase = nomFigureBase;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Collection<FigureRamli> getFigures() {
        return figures;
    }

    // 🔽 AJOUTE CECI :
    public void setFigures(Collection<FigureRamli> figures) {
        this.figures = figures;
    }
}
