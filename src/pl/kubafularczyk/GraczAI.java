package pl.kubafularczyk;

import java.util.Random;

public class GraczAI extends Gracz {

    private final Random RANDOM = new Random();

    @Override
    public PolozenieStatku wybierzPolozenie(int rozmiarPlanszy) {

        int x = RANDOM.nextInt(rozmiarPlanszy);
        int y = RANDOM.nextInt(rozmiarPlanszy);
        Pozycja pozycja = new Pozycja(x, y);
        PolozenieStatku.Orientacja orientacja = RANDOM.nextBoolean() ? PolozenieStatku.Orientacja.POZIOMA : PolozenieStatku.Orientacja.PIONOWA;
        PolozenieStatku polozenieStatku = new PolozenieStatku(pozycja, orientacja);

        return polozenieStatku;
    }

    @Override
    public Pozycja podajPozycjeStrzalu(int rozmiarPlanszy) {

        int pozycjaX = RANDOM.nextInt(rozmiarPlanszy);
        int pozycjaY = RANDOM.nextInt(rozmiarPlanszy);
        Pozycja pozycja = new Pozycja(pozycjaX, pozycjaY);
        return pozycja;
    }
}
