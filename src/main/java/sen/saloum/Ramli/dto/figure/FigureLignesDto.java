package sen.saloum.Ramli.dto.figure;

public class FigureLignesDto {
    private Long id;
    private String valeurs;
    private int position;
    private int ligneIndex;
    private Long figureId;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getValeurs() { return valeurs; }
    public void setValeurs(String valeurs) { this.valeurs = valeurs; }

    public int getPosition() { return position; }
    public void setPosition(int position) { this.position = position; }

    public int getLigneIndex() { return ligneIndex; }
    public void setLigneIndex(int ligneIndex) { this.ligneIndex = ligneIndex; }

    public Long getFigureId() { return figureId; }
    public void setFigureId(Long figureId) { this.figureId = figureId; }

}
