package sen.saloum.Ramli.dto.tirage;

import lombok.Getter;
import lombok.Setter;
import sen.saloum.Ramli.enums.NomFigureBase;
import sen.saloum.Ramli.enums.TypeFigure;

import java.time.OffsetDateTime;


public class TirageDto {
    private Long id;
    private String nomTirage;
    private String question;
    private String nomConsultant;
    private OffsetDateTime dateTirage;
    private String interpretation;
    private Long utilisateurId;
    private String valeurs;
    private NomFigureBase nomFigureBase;
    private TypeFigure typeFigure;
    private Long version;
    public TirageDto() {
    }

    public TirageDto(Long id,Long version, NomFigureBase nomFigureBase,TypeFigure typeFigure, String nomTirage,
                     String question, String nomConsultant, OffsetDateTime dateTirage,
                     String interpretation, Long utilisateurId, String valeurs
    ) {
        this.id = id;
        this.nomTirage = nomTirage;
        this.question = question;
        this.nomConsultant = nomConsultant;
        this.dateTirage = dateTirage;
        this.interpretation = interpretation;
        this.utilisateurId = utilisateurId;
        this.valeurs = valeurs;
        this.nomFigureBase=nomFigureBase;
        this.typeFigure=typeFigure;
        this.version=version;
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getNomConsultant() {
        return nomConsultant;
    }

    public void setNomConsultant(String nomConsultant) {
        this.nomConsultant = nomConsultant;
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

    public Long getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Long utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public String getValeurs() {
        return valeurs;
    }

    public void setValeurs(String valeurs) {
        this.valeurs = valeurs;
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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}