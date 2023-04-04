package pl.kubafularczyk;

import java.util.ArrayList;
import java.util.List;

// interface?
public abstract class Gracz {

    private Statek[] statki;
    private List<Pozycja> strzaly;

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
}
