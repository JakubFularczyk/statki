package pl.kubafularczyk;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// interface?
public abstract class Gracz {

    private Statek[] statki;
    private List<Pozycja> strzaly;
    protected final Scanner SCANNER = new Scanner(System.in);

    public Gracz() {
        this.strzaly = new ArrayList<>();
    }

    public Statek[] getStatki() {
        return statki;
    }

    public void setStatki(Statek[] statki) {
        this.statki = statki;
    }

    public List<Pozycja> getStrzaly() {
        return strzaly;
    }

    public void setStrzaly(List<Pozycja> strzaly) {
        this.strzaly = strzaly;
    }

    public void addStrzal(Pozycja pozycja) {
        this.strzaly.add(pozycja);
    }

    public abstract PolozenieStatku wybierzPolozenie(int rozmiarPlanszy);

    public abstract Pozycja podajPozycjeStrzalu(int rozmiarPlanszy);
}
