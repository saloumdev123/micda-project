package sen.saloum.Ramli.dto.tirage;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;
@Setter
@Getter
@Component
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

}
