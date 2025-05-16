package sen.saloum.Ramli.utils;

import java.util.ArrayList;
import java.util.List;

public class FigureUtils {

    /**
     * Crée une figure composée à partir de deux figures binaires via l'opération XOR.
     * Le XOR (^) permet de représenter les influences ésotériques entre deux figures.
     * Ex : [1, 0, 1, 1] XOR [0, 1, 1, 0] => [1, 1, 0, 1]
     */
    public static List<Integer> creerFigureComposee(List<Integer> f1, List<Integer> f2) {
        List<Integer> composee = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            composee.add(f1.get(i) ^ f2.get(i)); // XOR bit à bit
        }
        return composee;
    }

}
