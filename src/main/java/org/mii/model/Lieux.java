package org.mii.model;

import java.util.ArrayList;
import java.util.List;

public record Lieux(String nom, List<Rues> rues) {
    public Lieux(String nom) {
        this(nom, new ArrayList<>());
    }

    public void addRue(Rues rue) {
        this.rues.add(rue);
    }

    public Rues getRue(Lieux lieux1, Lieux lieux2) {
        for (Rues rue : rues) {
            if ((rue.getLieu1().equals(lieux1) && rue.getLieu2().equals(lieux2)) ||
                    (rue.getLieu1().equals(lieux2) && rue.getLieu2().equals(lieux1))) {
                return rue;
            }
        }
        return null;
    }

    public List<Lieux> getVoisinages() {
        List<Lieux> voisin = new ArrayList<>();
        for (Rues rue : rues) {
            Lieux autreLieux = rue.getAutreLieux(this);
            if (autreLieux != null) {
                voisin.add(autreLieux);
            }
        }
        return voisin;
    }

    @Override
    public String toString() {
        return "Lieu{" +
                "nom='" + nom + '\'' +
                '}';
    }
}
