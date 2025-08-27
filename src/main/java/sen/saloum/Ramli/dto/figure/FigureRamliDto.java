package sen.saloum.Ramli.dto.figure;

import sen.saloum.Ramli.enums.NomFigureBase;
import sen.saloum.Ramli.enums.TypeFigure;

import java.util.List;
public class FigureRamliDto {
    private Long id;
    private String nomFigure;
    private String description;
    private String symbolisme;
    private List<FigureLignesDto> lignes;


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

    public List<FigureLignesDto> getLignes() {
        return lignes;
    }

    public void setLignes(List<FigureLignesDto> lignes) {
        this.lignes = lignes;
    }
}
