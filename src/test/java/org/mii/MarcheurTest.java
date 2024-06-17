package org.mii;

import org.junit.jupiter.api.Test;
import org.mii.model.Carte;
import org.mii.model.Lieux;
import org.mii.model.Rues;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class MarcheurTest {
    @Test
    public void bjarni_marche_aleatoirement_de_l_hei_a_esti() {
        Marcheur marcheurBlanc1 = new Marcheur();
        Marcheur marcheurBlanc2 = new Marcheur();

        var marais = new Lieux("Marais");
        var sekolintsika = new Lieux("Sekolintsika");
        var hei = new Lieux("HEI");
        var pullman = new Lieux("Pullman");
        var nexta = new Lieux("Nexta");
        var balancoire = new Lieux("Balancoire");
        var boulevard = new Lieux("Boulevard de l'Europe");
        var esti = new Lieux("ESTI");

        Lieux[] lieuxArray1 = {marais, sekolintsika};
        Lieux[] lieuxArray2 = {sekolintsika, hei};
        Lieux[] lieuxArray3 = {hei, pullman};
        Lieux[] lieuxArray4 = {hei, balancoire};
        Lieux[] lieuxArray5 = {pullman, nexta};
        Lieux[] lieuxArray6 = {pullman, balancoire};
        Lieux[] lieuxArray7 = {balancoire, boulevard};
        Lieux[] lieuxArray8 = {balancoire, esti};
        Lieux[] lieuxArray9 = {boulevard, esti};

        var r_marais_sekolintsika = new Rues("Marais_Sekolintsika", lieuxArray1);
        var r_sekolintsika_hei = new Rues("Sekolintsika_HEI", lieuxArray2);
        var r_andriantsihorana = new Rues("Rue Andriantsihorana", lieuxArray3);
        var r_hei_balancoire = new Rues("HEI_Balancoire", lieuxArray4);
        var r_pullman_nexta = new Rues("Pullman_NEXTA", lieuxArray5);
        var r_ranaivo = new Rues("Rue Ranaivo", lieuxArray6);
        var r_balancoire_boulevard = new Rues("Balancoire_Boulevard", lieuxArray7);
        var r_balancoire_esti = new Rues("Balancoire_ESTI", lieuxArray8);
        var r_boulevard_esti = new Rues("Boulevard_ESTI", lieuxArray9);

        List<Lieux> lieuxDAntananarivo = List.of(marais, hei, pullman, nexta, balancoire, boulevard, esti);
        List<Rues> ruesDAntananarivo = List.of(r_marais_sekolintsika, r_sekolintsika_hei, r_andriantsihorana,
                r_hei_balancoire, r_pullman_nexta, r_ranaivo, r_balancoire_boulevard, r_balancoire_esti, r_boulevard_esti);

        Carte antananarivo = new Carte(lieuxDAntananarivo, ruesDAntananarivo);

        List<Lieux> chemin1 = marcheurBlanc1.marche(hei, esti, antananarivo);
        List<Lieux> chemin2 = marcheurBlanc2.marche(hei, esti, antananarivo);

        assertNotEquals(chemin1, chemin2);
    }
}