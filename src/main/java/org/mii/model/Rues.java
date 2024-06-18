package org.mii.model;

import lombok.Data;

@Data
public final class Rues {
    private final String nom;
    private final Lieux lieu1;
    private final Lieux lieu2;
    private int passages;

    public Rues(String nom, Lieux lieu1, Lieux lieu2) {
        this.nom = nom;
        this.lieu1 = lieu1;
        this.lieu2 = lieu2;
        lieu1.addRue(this);
        lieu2.addRue(this);
        this.passages = 0;
    }

    public Lieux getAutreLieux(Lieux lieux) {
        if (lieux.equals(lieu1)) return lieu2;
        else if (lieux.equals(lieu2)) return lieu1;
        else return null;
    }

    public void increment() {
        passages++;
    }
}
