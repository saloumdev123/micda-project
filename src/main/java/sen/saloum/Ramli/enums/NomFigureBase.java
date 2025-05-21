package sen.saloum.Ramli.enums;

import lombok.Getter;

@Getter
public enum NomFigureBase {
    VIA("La Voie"),
    POPULUS("Le Peuple"),
    FORTUNA_MAJOR("Grande Fortune"),
    FORTUNA_MINOR("Petite Fortune"),
    ACQUISITIO("Acquisition"),
    LAETITIA("Joie"),
    TRISTITIA("Tristesse"),
    PUELLA("La Jeune Fille"),
    PUER("Le Jeune Homme"),
    RUBEUS("Rouge"),
    ALBUS("Blanc"),
    CONJUNCTIO("Conjonction"),
    AMISSIO("Perte"),
    CAPUT_DRACONIS("Tête du Dragon"),
    CAUDA_DRACONIS("Queue du Dragon"),
    CARCER("Prison");
    private final String label;

    NomFigureBase(String label) {
        this.label = label;
    }

}
