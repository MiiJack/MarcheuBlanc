package org.mii.model;

import java.util.ArrayList;
import java.util.List;

public record Carte(List<Lieux> lieux, List<Rues> rues) {
    public List<Lieux> getVoisinages(Lieux lieux) {
        List<Lieux> voisin = new ArrayList<>();
        for (Rues rue : rues) {
            Lieux autreLieux = rue.getAutreLieux(lieux);
            if (autreLieux != null) {
                voisin.add(autreLieux);
            }
        }
        return voisin;
    }
}
