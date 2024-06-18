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

        var r_marais_sekolintsika = new Rues("Marais_Sekolintsika", marais, sekolintsika);
        var r_sekolintsika_hei = new Rues("Sekolintsika_HEI", sekolintsika, hei);
        var r_andriantsihorana = new Rues("Rue Andriantsihorana", hei, pullman);
        var r_hei_balancoire = new Rues("HEI_Balancoire", hei, balancoire);
        var r_pullman_nexta = new Rues("Pullman_NEXTA", pullman, nexta);
        var r_ranaivo = new Rues("Rue Ranaivo", pullman, balancoire);
        var r_balancoire_boulevard = new Rues("Balancoire_Boulevard", balancoire, boulevard);
        var r_balancoire_esti = new Rues("Balancoire_ESTI", balancoire, esti);
        var r_boulevard_esti = new Rues("Boulevard_ESTI", boulevard, esti);

        List<Lieux> lieuxDAntananarivo = List.of(marais, hei, pullman, nexta, balancoire, boulevard, esti);
        List<Rues> ruesDAntananarivo = List.of(r_marais_sekolintsika, r_sekolintsika_hei, r_andriantsihorana,
                r_hei_balancoire, r_pullman_nexta, r_ranaivo, r_balancoire_boulevard, r_balancoire_esti, r_boulevard_esti);

        Carte antananarivo = new Carte(lieuxDAntananarivo, ruesDAntananarivo);

        List<Lieux> chemin1 = marcheurBlanc1.marche(hei, esti);
        List<Lieux> chemin2 = marcheurBlanc2.marche(hei, esti);

        assertNotEquals(chemin1, chemin2);

        // Check if chemin1 and chemin2 are valid paths in the Carte
        assertValidPath(antananarivo, chemin1);
        assertValidPath(antananarivo, chemin2);

        // Check if the first element is "HEI" and the last element is "ESTI"
        assertFirstLastElement(chemin1, hei, esti);
        assertFirstLastElement(chemin2, hei, esti);
    }

    private void assertValidPath(Carte carte, List<Lieux> chemin) {
        for (int i = 0; i < chemin.size() - 1; i++) {
            Lieux current = chemin.get(i);
            Lieux next = chemin.get(i + 1);
            Rues road = carte.rues().stream()
                    .filter(r -> (r.getLieu1().equals(current) && r.getLieu2().equals(next)) ||
                            (r.getLieu1().equals(next) && r.getLieu2().equals(current)))
                    .findFirst()
                    .orElse(null);
            assert road != null : "Invalid path: No road between " + current + " and " + next;
        }
    }

    private void assertFirstLastElement(List<Lieux> chemin, Lieux debut, Lieux fin) {
        assert chemin.getFirst().equals(debut) : "First element is not " + debut;
        assert chemin.getLast().equals(fin) : "Last element is not " + fin;
    }

}