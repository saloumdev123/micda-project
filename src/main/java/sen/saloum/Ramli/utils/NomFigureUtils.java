package sen.saloum.Ramli.utils;

import sen.saloum.Ramli.enums.NomFigureBase;

import java.util.*;
import java.util.stream.Collectors;

public class NomFigureUtils {

    private static final Map<String, NomFigureBase> FIGURE_MAP = Map.ofEntries(
            Map.entry("0000", NomFigureBase.VIA),
            Map.entry("1111", NomFigureBase.POPULUS),
            Map.entry("1000", NomFigureBase.PUER),
            Map.entry("0111", NomFigureBase.PUELLA),
            Map.entry("0100", NomFigureBase.FORTUNA_MAJOR),
            Map.entry("0010", NomFigureBase.FORTUNA_MINOR),
            Map.entry("1010", NomFigureBase.ACQUISITIO),
            Map.entry("0101", NomFigureBase.AMISSIO),
            Map.entry("1100", NomFigureBase.LAETITIA),
            Map.entry("0011", NomFigureBase.TRISTITIA),
            Map.entry("0110", NomFigureBase.CARCER),
            Map.entry("1001", NomFigureBase.CONJUNCTIO),
            Map.entry("1110", NomFigureBase.RUBEUS),
            Map.entry("0001", NomFigureBase.ALBUS),
            Map.entry("1101", NomFigureBase.CAPUT_DRACONIS),
            Map.entry("1011", NomFigureBase.CAUDA_DRACONIS)
    );

    /** 🔹 Obtenir le nom d’une figure à partir de ses bits */
    public static NomFigureBase getNomFigureBase(List<Integer> figure) {
        String key = figureToString(figure);
        return FIGURE_MAP.getOrDefault(key, null);
    }

    /** 🔹 Tous les noms possibles pour vérification dans les tests */
    public static Set<NomFigureBase> getToutesLesFigures() {
        return new HashSet<>(FIGURE_MAP.values());
    }

    private static String figureToString(List<Integer> figure) {
        return figure.stream().map(String::valueOf).collect(Collectors.joining());
    }
}
