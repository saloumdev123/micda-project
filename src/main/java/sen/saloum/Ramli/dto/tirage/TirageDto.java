package sen.saloum.Ramli.dto.tirage;

import sen.saloum.Ramli.dto.figure.FigureRamliDto;
import sen.saloum.Ramli.models.Utilisateur;

import java.time.OffsetDateTime;
import java.util.List;

public class TirageDto {
    private Long id;
    private String nomTirage;
    private String figureResultats;
    private String question;
    private String nomConsultant;
    private OffsetDateTime dateTirage;
    private String interpretation;
    private Long utilisateurId;
    private List<FigureRamliDto> figures;
    private String valeurs;


    public TirageDto() {
    }

    public TirageDto(Long id, String nomTirage, String figureResultats, String question, String nomConsultant, OffsetDateTime dateTirage, String interpretation, Long utilisateurId, List<FigureRamliDto> figures, String valeurs, Utilisateur utilisateur) {
        this.id = id;
        this.nomTirage = nomTirage;
        figureResultats = figureResultats;
        this.question = question;
        this.nomConsultant = nomConsultant;
        this.dateTirage = dateTirage;
        this.interpretation = interpretation;
        this.utilisateurId = utilisateurId;
        this.figures = figures;
        this.valeurs = valeurs;
        this.utilisateurId = utilisateurId;
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

    public String getNomConsultant() {
        return nomConsultant;
    }

    public void setNomConsultant(String nomConsultant) {
        this.nomConsultant = nomConsultant;
    }

    public List<FigureRamliDto> getFigures() {
        return figures;
    }

    public void setFigures(List<FigureRamliDto> figures) {
        this.figures = figures;
    }

    public String getValeurs() {
        return valeurs;
    }

    public void setValeurs(String valeurs) {
        this.valeurs = valeurs;
    }

    public String getFigureResultats() {
        return figureResultats;
    }

    public void setFigureResultats(String figureResultats) {
        this.figureResultats = figureResultats;
    }
}
