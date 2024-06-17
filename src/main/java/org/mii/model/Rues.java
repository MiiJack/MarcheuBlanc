package org.mii.model;

import lombok.Data;

@Data
public final class Rues {
    private final String nom;
    private final Lieux[] lieuxRelies;
    private int passages;

    public Rues(String nom, Lieux[] lieuxRelies) {
        this.nom = nom;
        this.lieuxRelies = lieuxRelies;
        this.passages = 0;
    }

    public Lieux getAutreLieux(Lieux lieux) {
        if (lieux.equals(lieuxRelies[0])) return lieuxRelies[1];
        else if (lieux.equals(lieuxRelies[1])) return lieuxRelies[0];
        else return null;
    }

    public void increment() {
        passages++;
    }
}
