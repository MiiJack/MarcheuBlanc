package org.mii.model;

public record Rues(String nom, Lieux[] lieuxRelies) {
    public Lieux getAutreLieux(Lieux lieux) {
        if (lieux.equals(lieuxRelies[0])) return lieuxRelies[1];
        else if (lieux.equals(lieuxRelies[1])) return lieuxRelies[0];
        else return null;
    }
}
