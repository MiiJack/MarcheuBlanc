package org.mii;

import org.mii.model.Lieux;
import org.mii.model.Rues;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public final class Marcheur {
    private static final Random random = new Random();

    private static int getRuePassages(Lieux lieux1, Lieux lieux2) {
        List<Rues> rues1 = new ArrayList<>(lieux1.rues());
        List<Rues> rues2 = new ArrayList<>(lieux2.rues());
        rues1.retainAll(rues2);
        return rues1.isEmpty() ? 0 : rues1.getFirst().getPassages();
    }

    private static List<Lieux> getVoisinsAvecMoinsDePassages(Lieux actuel, List<Lieux> voisins) {
        return voisins.stream()
                .sorted(Comparator.comparingInt(v -> getRuePassages(actuel, v)))
                .collect(ArrayList::new, (list, v) -> {
                    if (list.isEmpty() || getRuePassages(actuel, v) == getRuePassages(actuel, list.getFirst())) {
                        list.add(v);
                    }
                }, ArrayList::addAll);
    }

    public List<Lieux> marche(Lieux debut, Lieux fin) {
        List<Lieux> chemin = new ArrayList<>();
        Lieux actuel = debut;
        chemin.add(debut);

        while (!actuel.equals(fin)) {
            List<Lieux> voisins = actuel.getVoisinages();
            if (voisins.isEmpty()) {
                break;
            }

            List<Lieux> voisinsAvecMoinsDePassages = getVoisinsAvecMoinsDePassages(actuel, voisins);
            Lieux prochainLieux = voisinsAvecMoinsDePassages.get(random.nextInt(voisinsAvecMoinsDePassages.size()));
            Rues rueVersProchain = prochainLieux.getRue(actuel, prochainLieux);
            if (rueVersProchain != null) {
                rueVersProchain.increment();
            }

            actuel = prochainLieux;
            chemin.add(actuel);
        }
        return chemin;
    }
}
