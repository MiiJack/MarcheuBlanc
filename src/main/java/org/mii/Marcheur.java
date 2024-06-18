package org.mii;

import org.mii.model.Carte;
import org.mii.model.Lieux;
import org.mii.model.Rues;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public final class Marcheur {
    private static final Random random = new Random();

    private static int getRuePassages(Carte carte, Lieux lieux1, Lieux lieux2) {
        Rues rueVersVoisin = carte.getRue(lieux1, lieux2);
        return rueVersVoisin != null ? rueVersVoisin.getPassages() : Integer.MAX_VALUE;
    }

    private static List<Lieux> getVoisinsAvecMoinsDePassages(Carte carte, Lieux actuel, List<Lieux> voisins) {
        return voisins.stream()
                .sorted(Comparator.comparingInt(v -> getRuePassages(carte, actuel, v)))
                .collect(ArrayList::new, (list, v) -> {
                    if (list.isEmpty() || getRuePassages(carte, actuel, v) == getRuePassages(carte, actuel, list.getFirst())) {
                        list.add(v);
                    }
                }, ArrayList::addAll);
    }

    public List<Lieux> marche(Lieux debut, Lieux fin, Carte carte) {
        List<Lieux> chemin = new ArrayList<>();
        Lieux actuel = debut;
        chemin.add(debut);

        while (!actuel.equals(fin)) {
            List<Lieux> voisins = carte.getVoisinages(actuel);
            if (voisins.isEmpty()) {
                break;
            }

            List<Lieux> voisinsAvecMoinsDePassages = getVoisinsAvecMoinsDePassages(carte, actuel, voisins);

            Lieux prochainLieux = voisinsAvecMoinsDePassages.get(random.nextInt(voisinsAvecMoinsDePassages.size()));
            Rues rueVersProchain = carte.getRue(actuel, prochainLieux);
            if (rueVersProchain != null) {
                rueVersProchain.increment();
            }

            actuel = prochainLieux;
            chemin.add(actuel);
        }
        return chemin;
    }
}
