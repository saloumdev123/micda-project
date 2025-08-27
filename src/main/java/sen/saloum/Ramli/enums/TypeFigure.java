package sen.saloum.Ramli.enums;

public enum TypeFigure {
    TEMOIN_1("Témoin Gauche"),
    TEMOIN_2("Témoin Droit"),
    TEMOIN_3("Témoin 3"),
    TEMOIN_4("Témoin 4"),
    FILLE_1("Fille 1"),
    FILLE_2("Fille 2"),
    FILLE_3("Fille 3"),
    FILLE_4("Fille 4"),
    NIECE_1("Nièce 1"),
    NIECE_2("Nièce 2"),
    JUGE("Juge"),
    RECONCILIATEUR("Réconciliateur");

    private final String label;
    TypeFigure(String label) { this.label = label; }
    public String getLabel() { return label; }
}
