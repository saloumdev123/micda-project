package sen.saloum.Ramli.dto.tirage;

import sen.saloum.Ramli.dto.figure.FigureRamliDto;
import sen.saloum.Ramli.dto.figure.InterpretationDto;
import sen.saloum.Ramli.dto.user.UtilisateurDto;
import sen.saloum.Ramli.enums.NomFigureBase;
import sen.saloum.Ramli.enums.TypeFigure;
import sen.saloum.Ramli.models.Utilisateur;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;


public class TirageDto {

    private Long id;
    private LocalDateTime dateTirage;
    private UtilisateurDto utilisateur;
    private List<FigureRamliDto> figures;
    private InterpretationDto interpretation;

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

    public UtilisateurDto getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(UtilisateurDto utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<FigureRamliDto> getFigures() {
        return figures;
    }

    public void setFigures(List<FigureRamliDto> figures) {
        this.figures = figures;
    }

    public InterpretationDto getInterpretation() {
        return interpretation;
    }

    public void setInterpretation(InterpretationDto interpretation) {
        this.interpretation = interpretation;
    }
}