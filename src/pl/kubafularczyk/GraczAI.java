package pl.kubafularczyk;

import java.util.Random;

public class GraczAI extends Gracz {


    @Override
    public PolozenieStatku wybierzPolozenie(int rozmiarPlanszy) {
        Random random = new Random();
        int x = random.nextInt(rozmiarPlanszy);
        int y = random.nextInt(rozmiarPlanszy);
        Pozycja pozycja = new Pozycja(x, y);
        PolozenieStatku.Orientacja orientacja = random.nextBoolean() ? PolozenieStatku.Orientacja.POZIOMA : PolozenieStatku.Orientacja.PIONOWA;
        PolozenieStatku polozenieStatku = new PolozenieStatku(pozycja, orientacja);

        return polozenieStatku;
    }
}
