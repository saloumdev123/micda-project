package sen.saloum.Ramli.dto.tirage;

import java.util.List;

public class DonneesDeBaseDto {
    private List<Integer> valeursInitiales;  // Exemple de liste de valeurs initiales pour le tirage
    private String question;                  // Question pour le tirage (optionnel, mais souvent utilisé)
    private String typeTirage;               // Type de tirage (par exemple, "tirage aléatoire", "tirage par sélection", etc.)
    private Integer nombreDeLignes;          // Nombre de lignes à générer dans le tirage
    private Integer nombreDeFigures;         // Nombre de figures à générer
    private String mode;                     // Mode de tirage (optionnel : exemple "automatique", "manuel", etc.)

    public DonneesDeBaseDto() {
    }

    public DonneesDeBaseDto(List<Integer> valeursInitiales, String question, String typeTirage, Integer nombreDeLignes, Integer nombreDeFigures, String mode) {
        this.valeursInitiales = valeursInitiales;
        this.question = question;
        this.typeTirage = typeTirage;
        this.nombreDeLignes = nombreDeLignes;
        this.nombreDeFigures = nombreDeFigures;
        this.mode = mode;
    }

    public List<Integer> getValeursInitiales() {
        return valeursInitiales;
    }

    public void setValeursInitiales(List<Integer> valeursInitiales) {
        this.valeursInitiales = valeursInitiales;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getTypeTirage() {
        return typeTirage;
    }

    public void setTypeTirage(String typeTirage) {
        this.typeTirage = typeTirage;
    }

    public Integer getNombreDeLignes() {
        return nombreDeLignes;
    }

    public void setNombreDeLignes(Integer nombreDeLignes) {
        this.nombreDeLignes = nombreDeLignes;
    }

    public Integer getNombreDeFigures() {
        return nombreDeFigures;
    }

    public void setNombreDeFigures(Integer nombreDeFigures) {
        this.nombreDeFigures = nombreDeFigures;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
