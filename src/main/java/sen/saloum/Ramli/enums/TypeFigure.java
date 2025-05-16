package sen.saloum.Ramli.enums;

import lombok.Getter;

@Getter
public enum TypeFigure {
    TEMOIN_DROIT("Témoin Droit"),
    TEMOIN_GAUCHE("Témoin Gauche"),
    JUGE("Juge"),
    JUGE_TEMOIN("Juge Témoin"),
    TETE("Tête"),
    QUEUE("Queue"),
    TEMOIN_1("Témoin 1"),
    TEMOIN_2("Témoin 2"),
    TEMOIN_3("Témoin 3"),
    TEMOIN_4("Témoin 4"),

    // Filles
    FILLE_1("Fille 1"),
    FILLE_2("Fille 2"),
    FILLE_3("Fille 3"),
    FILLE_4("Fille 4"),

    // Nièces
    NIECE_1("Nièce 1"),
    NIECE_2("Nièce 2");

    private final String label;

    TypeFigure(String label) {
        this.label = label;
    }

}
