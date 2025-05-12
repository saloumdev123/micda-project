package sen.saloum.Ramli.enums;

public enum TypeFigure {
    TEMOIN_DROIT("Témoin Droit"),
    TEMOIN_GAUCHE("Témoin Gauche"),
    JUGE("Juge"),
    JUGE_TEMOIN("Juge Témoin"),
    TETE("Tête"),
    QUEUE("Queue");

    private final String label;

    TypeFigure(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
  }
