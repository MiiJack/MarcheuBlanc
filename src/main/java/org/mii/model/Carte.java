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

    public Rues getRue(Lieux lieux1, Lieux lieux2) {
        for (Rues rue : rues) {
            if ((rue.getLieuxRelies()[0].equals(lieux1) && rue.getLieuxRelies()[1].equals(lieux2)) ||
                    (rue.getLieuxRelies()[0].equals(lieux2) && rue.getLieuxRelies()[1].equals(lieux1))) {
                return rue;
            }
        }
        return null;
    }
}
