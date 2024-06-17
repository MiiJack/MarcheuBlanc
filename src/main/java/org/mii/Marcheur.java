package org.mii;

import org.mii.model.Carte;
import org.mii.model.Lieux;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class Marcheur {
    private static final Random random = new Random();

    public List<Lieux> marche(Lieux debut, Lieux fin, Carte carte) {
        List<Lieux> chemin = new ArrayList<>();
        Lieux actuel = debut;
        chemin.add(debut);

        while (!actuel.equals(fin)) {
            List<Lieux> voisin = carte.getVoisinages(actuel);
            if (voisin.isEmpty()) {
                break;
            }
            actuel = voisin.get(random.nextInt(voisin.size()));
            chemin.add(actuel);
        }
        return chemin;
    }
}
